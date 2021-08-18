f = open('delay.txt','a')
f.write('오늘 지각자 \n')
while True:
    n = input('오늘 지각자를 쓰시오 (종료: q) ')
    if 'q' == n:
        break
    f.write(n)
    f.writelines('\n')
f.close()