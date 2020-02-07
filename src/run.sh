#!/bin/bash
n=100
for i in `seq 5`
do
    java EssaiPipo ../tests/CRexemple.txt $n > ../tests/test_results/generated_text_$n.txt
    java EssaiTextAnalyser ../tests/test_results/generated_text_$n.txt > ../tests/test_results/generated_text_$n\_stats.txt
    n=`expr $n \* 10`
done

    java EssaiTextAnalyser ../tests/CRexemple.txt > ../tests/CRexemple_stats.txt



