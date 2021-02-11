from twilio.jwt.access_token import AccessToken
from twilio.jwt.access_token.grants import ChatGrant

# required for all twilio access tokens
account_sid = 'AC7a6351ad5a1ff9a5ffd302b3b5c0f08a'
api_key = 'SK58068f04409f7dd9d7bb78f57a05c2f1'
api_secret = 'xxxxxxxxxxxxxx'

# required for Chat grants
service_sid = 'ISxxxxxxxxxxxx'
identity = 'user@example.com'

# Create access token with credentials
token = AccessToken(account_sid, api_key, api_secret, identity=identity)

# Create an Chat grant and add to token
chat_grant = ChatGrant(service_sid=service_sid)
token.add_grant(chat_grant)

# Return token info as JSON
print(token.to_jwt())
