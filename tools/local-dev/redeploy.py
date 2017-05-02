#!/usr/bin/env python3
import subprocess
import os

currentWorkingDirectory = os.getcwd()

# stop the api container
subprocess.run(["docker", "stop", "interview-api"])

# remove the api container
subprocess.run(["docker", "rm", "interview-api"])

# rebuild
# we live in interview/tools/local-dev, so go up to interview
os.chdir("../../")
subprocess.run(["mvn", "clean", "package"])

# bring back up the api container
os.chdir(currentWorkingDirectory)
subprocess.run(["docker-compose", "up", "-d"])