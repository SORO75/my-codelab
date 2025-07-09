import requests
'''
with open('url.txt', 'r') as file:
    url = file.read().strip()

r = requests.get(url)
content =r.json()

print(content['articles'][1]['title'])
print(content['articles'][1]['description'])

articles = content['articles']
print(type(articles))
'''


def get_news(country, api_key='890603a55bfa47048e4490069ebee18c'):
    url =f'https://newsapi.org/v2/top-headlines?country={country}everything?&apiKey={api_key}'
    r =requests.get(url)
    content = r.json()
    articles = content['articles']
    results =[]
    for article in articles:
        results.append(f"TITLE\n', {article['title']},'\nDESCRIPTION\n',  {article['description']}")
    return results


print(get_news(country='us'))
