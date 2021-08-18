import urllib.request

from bs4 import BeautifulSoup
import requests
cnt = 1
while cnt < 40:
    i = str(cnt)
    url = 'https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&page='+i
    html = urllib.request.urlopen(url).read()
    soup = BeautifulSoup(html,"html.parser")
    #영화제목:링크: 평점
    tb = soup.select_one(".list_ranking")
    trs = tb.find_all("tr")
    for tr in trs :
        el = tr.select_one(".tit5 a")
        if el:
            href = el.get("href")
            title = el.getText()
            point = tr.select_one(".point").get_text()
            print(title,point,href)
        print("-" * 50)
    cnt = cnt+1

