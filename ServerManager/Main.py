from flask import Flask, request, jsonify
from Factory import Factory

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

if __name__ == '__main__':
	app.run(host='0.0.0.0')
