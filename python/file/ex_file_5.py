import cx_Oracle
conn = cx_Oracle.connect('java','oracle','127.0.0.1:1521/xe')
cur = conn.cursor()

var2 = cur.var(cx_Oracle.STRING)
var3 = cur.var(cx_Oracle.STRING)
cur.callproc('my_parameter_test_proc',('hi',var2,var3))
print(var2.values, var3.values)