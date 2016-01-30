###!/usr/bin/python3

import urllib.request as req
from bs4 import BeautifulSoup
import re

class Search:
  def __init__(self, phrase):
    splitted_phrase = phrase.split(' ')
    self.search_term = ""
    if(splitted_phrase[0] == "search"):
      self.search_term = ' '.join(splitted_phrase[1:]).replace(' ', '%20')
    elif(splitted_phrase[0] == "what" or splitted_phrase[0] == "when" or splitted_phrase[0] == "how"):
      self.search_term = phrase.replace(' ', '%20')
    self.result = []

  def searching(self):
    request = req.Request("https://www.google.com/search?q="+self.search_term, headers={'User-Agent': 'Chrome/5.0'})
    

    html = req.urlopen(request)
    
    
    soup = BeautifulSoup(html , "html.parser")

    result = []


    for item in (soup.findAll("div", { "class" : "g" })):
      try:
        temp = {}
        url = item('cite')[0].text
        if ( (str(url)[:3] == "www" or str(url)[:3] == "htt") and "..." not in str(url) ):
          temp['url'] = str(url)

          title = str(item('a')[0].text.encode("utf-8"))[2:-1]
          temp['title'] = title

          snippet = str(item('span', {'class' : 'st'})[0].text.encode("utf-8"))[2:-1]
          temp['snippet'] = snippet

          result.append(temp)

      except:
        continue


    if len(result) < 10:
      request2 = req.Request("https://www.google.com/search?q="+self.search_term+"&start=10", headers={'User-Agent': 'Chrome/5.0'})
      html2 = req.urlopen(request2)
      soup2 = BeautifulSoup(html2 , "html.parser")

    
    for item in (soup.findAll("div", { "class" : "g" })):
      try:
        url = item('cite')[0].text
        if ( (str(url)[:3] == "www" or str(url)[:3] == "htt") and "..." not in str(url) ):
          temp['url'] = str(url)

          title = str(item('a')[0].text.encode("utf-8"))[2:-1]
          temp['title'] = title

          snippet = str(item('span', {'class' : 'st'})[0].text.encode("utf-8"))[2:-1]
          temp['snippet'] = snippet

          result.append(temp)

      except:
        continue
          
        
    if(len(result) > 10):
      return result[:10]
    else:
      return result


# a = Search("how is iran")
# print(a.searching())