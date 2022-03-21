import sys

for line in sys.stdin:
    # Read the request
    req = line.rstrip()
    # print(f"Get the request: {req}")

    # Response
    print("1,\"[12.342]\",\"[1563]\",\"[452]\"")
    sys.stdout.flush()
