#!/bin/bash
#SBATCH --nodes=1
#SBATCH --output=linsort2GB.log

echo 'start linsort2GB'
time sort /input/data-2GB.in > /tmp/new.txt
