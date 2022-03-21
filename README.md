# Java & Python Porting Test

This repository aims to test the following ways for communicating a Python process from a Java process:

- Sub-process standard input/output
- TCP Connections

## Project Structures

- `python`
    - `fork-estimate.py`: the Python script for the sub-process test
    - `tcp-server.py`: the Python script for the TCP connection test
- `java`: a [Eclipse](https://www.eclipse.org/downloads/) project that contains the code for benchmarking and all the connectors

## How to Run?

### Sub-process Test

To run the sub-process test, launch the Java process with the following configuration:

- Main Class: `slmt.porttest.MainClass`
- Program Arguments: `1 [Sending Times] [Python Path] [Script Dir]`
    - `[Sending Times]`: how many times the process sends a message to the Python process
    - `[Python Path]`: where the Python runtime is
    - `[Script Dir]`: where the script to test is (which is usually `../python`)

### TCP Connection Test

To run the TCP connection test, you must first launch the Python server by running `python tcp-server.py`.

Then, launch the Java process with the following configuration:

- Main Class: `slmt.porttest.MainClass`
- Program Arguments: `2 [Sending Times] "" ""`
    - `[Sending Times]`: how many times the process sends a message to the Python process

## Reference Result

Here is the result of a test running on the following environment:

- Hardware: Intel(R) Core(TM) i7-6900K CPU @ 3.20GHz
- OS: CentOS 7 (3.10.0-1160.41.1.el7.x86_64)
- Java: 1.8.0_302
- Python: 3.7.11

The average latency for each method is:

| Methods      | Sub-process | TCP Connection |
|--------------|-------------|----------------|
| Avg. Latency | 14 μs       | 24 μs          |

## License

Copyright 2022 Yu-Shan Lin

Licensed under [MIT License](https://github.com/SLMT/telnet-rs/blob/master/LICENSE)
