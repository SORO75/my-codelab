from flask import Flask, jsonify

from bs4 import BeautifulSoup
import requests

''''
def get_currency(in_currency, out_currency):
    url = f'https://www.x-rates.com/calculator/?from={in_currency}&to={out_currency}&amount=1'
    content = requests.get(url).text
    soup = BeautifulSoup(content, 'html.parser')
    rate = soup.find("span", class_="ccOutputRslt").get_text()
    rate = float(rate[:-4])

    return rate
'''
def get_currency(in_currency, out_currency):
    try:
        url = f'https://www.x-rates.com/calculator/?from={in_currency}&to={out_currency}&amount=1'
        content = requests.get(url, timeout=5).text
        soup = BeautifulSoup(content, 'html.parser')
        rate_span = soup.find("span", class_="ccOutputRslt")
        if not rate_span:
            raise ValueError("Konnte Wechselkurs nicht extrahieren.")
        rate = float(rate_span.get_text()[:-4])
        return rate
    except Exception as e:
        return None  # oder raise e für Debugging



app = Flask(__name__)


@app.route('/')
def home():
    return '<h1>Currency Rate API</h1> <p>Example URL: /api/v1/usd-eur</p>'

'''
@app.route('/api/v1/<in_cur>-<out_cur>')
def api(in_cur, out_cur):
    rate = get_currency(in_cur, out_cur)
    result_dictionary = {'input_currency': in_cur, 'output_currency': out_cur, 'rate': rate}
    return jsonify(result_dictionary)
'''

@app.route('/api/v1/<in_cur>-<out_cur>')
def api(in_cur, out_cur):
    rate = get_currency(in_cur.upper(), out_cur.upper())
    if rate is None:
        return jsonify({'error': 'Exchange rate could not be determined.'}), 500
    return jsonify({'input_currency': in_cur, 'output_currency': out_cur, 'rate': rate})


if __name__ == '__main__':
    app.run(debug=False, host='0.0.0.0', port=5555)
