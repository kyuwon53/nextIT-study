import os
print(os.getcwd())


str='''안녕하세요 '''
f = open('../day4/data/first.txt', 'w')
f.write(str)
f.close()

f = open('../day4/data/first.txt')
while True:
    line = f.readline()
    if len(line) == 0:
        break
    print(line, end='')
f.close()