# Download the helper library from https://www.twilio.com/docs/python/install
import os
from twilio.rest import Client
import sys
import requests
import tokenize
#import pytz
# Your Account Sid and Auth Token from twilio.com/console
# and set the environment variables. See http://twil.io/secure
account_sid = 'AC7a6351ad5a1ff9a5ffd302b3b5c0f08a'
auth_token = 'e73d9d7a39ce419b1509ed9e8b1ace42'
client = Client(account_sid, auth_token)

room = client.video.rooms.create(
                              record_participants_on_connect=True,
                              status_callback='http://example.org',
                              type='group',
                              unique_name=sys.argv[1]
                          )

print(room.sid)
