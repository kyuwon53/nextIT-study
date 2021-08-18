import sqlite3
#메모리 db 접속 ( 일회성 :memory:)
conn = sqlite3.connect(":memory:")
cur = conn.cursor()
cur.execute('''create table member(mem_id varchar(30), mem_age number);''')
conn.commit()
cur.execute('''insert into member values('sunja',20);''')
conn.commit()
cur.execute('''select count(*) from member''')
print(cur)

# 파일로 저장
conn = sqlite3.connect("ex.db")
cur = conn.cursor()
cur.execute('''create table stocks(data text, trans text,symbol text, qty real, price real);''')
conn.commit()
conn.close()
print(cur)