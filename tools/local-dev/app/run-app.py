#!/usr/bin/env python3
import argparse
import configparser
import os
import subprocess

# load the env script from the arguments
parser = argparse.ArgumentParser()
parser.add_argument("envFile")
args = parser.parse_args()

# open the env data
config = configparser.ConfigParser()
config.readfp(open(args.envFile))

# set those configuration values to env variables
for section in config.sections():
    for (key, value) in config.items(section):
        os.environ[key.upper()] = value

# start up the java process
subprocess.run(["java",
                    "-agentlib:jdwp=transport=dt_socket,server=y,address=8082,suspend=n",
                    "-jar", "/opt/interview/api/target/api.jar", "server", "/opt/interview/conf/configuration.yml"])