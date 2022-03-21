import socket
from urllib import request

HOST = "127.0.0.1"
PORT = 54321

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:

    # Initilize the socket
    sock.bind((HOST, PORT))
    sock.listen()
    
    print(f"The server is initialized at ({HOST}, {PORT})")

    # Accepet a new connection
    while (True):
        (conn, addr) = sock.accept()
        
        with conn:
            print(f"Connected by {addr}")

            while True:
                data = conn.recv(1024)
                if not data:
                    break
                req = data.decode()
                # print(f"received string: {req}")

                resp = "1,\"[12.342]\",\"[1563]\",\"[452]\"\n"
                conn.send(resp.encode())
                # print(f"sending response: {resp}")
