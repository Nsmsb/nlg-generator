#!/bin/bash

for lines_n in `seq 10`
do
    head -n $(($lines_n * 33)) ../tests/LesAchats > ../tests/test_files/LesAchats_$(($lines_n * 33))
done

for file in `ls ../tests/test_files/`
do
    java EssaiJoin ../tests/test_files/$file ../tests/LesVins ../tests/test_results/$file\_Nested ../tests/test_results/$file\_Hashed
done
