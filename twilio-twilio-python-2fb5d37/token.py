from twilio.jwt.access_token import AccessToken
from twilio.jwt.access_token.grants import VideoGrant
import os
import sys

# required for all twilio access tokens
# required for all twilio access tokens
account_sid = 'AC7a6351ad5a1ff9a5ffd302b3b5c0f08a'
api_key = 'SK57f1cf41380573124d33146f18c6b2bd'
api_secret = 'WEG8wGCiZ25s7YV4LynJqLKronviWAtw'

identity = sys.argv[1]

# Create Access Token with credentials
token = AccessToken(account_sid, api_key, api_secret, identity=identity)

# Create a Video grant and add to token
video_grant = VideoGrant(room=sys.argv[2])
token.add_grant(video_grant)

# Return token info as JSON
print(token.to_jwt())
