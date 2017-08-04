# Microservice based "Marketplace"

## Preparation
1. Generate `config-server.jks` keystore
2. Build base java image with JCE 
    ```bash
    ./setup/run_all
    ```

## Check config server
1. Run config server 
    ```bash
    docker run -p 8888:8888 -it net.ins.marketplace/config-server:0.0.1
    ``` 
2. Try to encrypt some value
    ```bash
    DECRYPTED=$(curl localhost:8888/encrypt -d foo)
    ```
3. Then check it sucessfully decrypted
    ```bash
    curl localhost:8888/decrypt -d $DECRYPTED
    ```