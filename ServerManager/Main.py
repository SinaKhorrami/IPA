from flask import Flask, request, jsonify
from Factory import Factory

import os, sys
sys.path.append(os.path.abspath("../Interests/"))
from interests import Interests

app = Flask(__name__)

@app.route("/", methods=['POST'])
def index():
	'''
		Json receiving request from client has 3 key
		[component] : destination component
		[message]   : content of user speech changed to text
		[device_id] : device id of client (unique)
	'''
	factory = Factory()
	component = request.json["component"]
	device_id = request.json["device_id"]
	message   = request.json["message"]
	result = factory.getResult(component, message, device_id)
	return jsonify(result), 200

@app.route("/setInterest", methods=['POST'])
def setInterest():
	'''
	'''
	device_id = request.json["id"]
	rq = {}
	rq['cat'] = request.json['cat']
	rq['val'] = request.json['val']
	obj = Interests(device_id)
	obj.setInterests(rq)
	return jsonify({}), 200

if __name__ == '__main__':
	app.run(host='0.0.0.0')
