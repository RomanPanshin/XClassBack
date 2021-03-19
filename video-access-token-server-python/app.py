import os
from flask import Flask, jsonify, request
from twilio.jwt.access_token import AccessToken
from twilio.jwt.access_token.grants import VideoGrant
from dotenv import load_dotenv, find_dotenv

app = Flask(__name__)
load_dotenv(find_dotenv())

@app.route('/')
def token():
    # Get credentials for environment variables
    account_sid = "ACa9489e89bea2fd6a143b64def1cf01f7"
    api_key = "SKe3a14561d59837544e4079f95e9b6616"
    api_secret = "4mxMGKNUjD80RhIZ1xGxtaP6ABCnIlUD"

    # Create an Access Token
    token = AccessToken(account_sid, api_key, api_secret)

    # Set the Identity of this token
    token.identity = request.values.get('identity') or 'identity'
    
    # Grant access to Video
    grant = VideoGrant()
    grant.room = request.values.get('room')
    token.add_grant(grant)

    # Return token
    return token.to_jwt()

if __name__ == '__main__':
    app.run(debug=True)
