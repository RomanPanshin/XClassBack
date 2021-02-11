# coding=utf-8
r"""
This code was generated by
\ / _    _  _|   _  _
 | (_)\/(_)(_|\/| |(/_  v1.0.0
      /       /
"""

from tests import IntegrationTestCase
from tests.holodeck import Request
from twilio.base.exceptions import TwilioException
from twilio.http.response import Response


class FlexFlowTestCase(IntegrationTestCase):

    def test_list_request(self):
        self.holodeck.mock(Response(500, ''))

        with self.assertRaises(TwilioException):
            self.client.flex_api.v1.flex_flow.list()

        self.holodeck.assert_has_request(Request(
            'get',
            'https://flex-api.twilio.com/v1/FlexFlows',
        ))

    def test_read_full_response(self):
        self.holodeck.mock(Response(
            200,
            '''
            {
                "meta": {
                    "page": 0,
                    "page_size": 50,
                    "first_page_url": "https://flex-api.twilio.com/v1/FlexFlows?PageSize=50&Page=0",
                    "previous_page_url": null,
                    "url": "https://flex-api.twilio.com/v1/FlexFlows?PageSize=50&Page=0",
                    "next_page_url": null,
                    "key": "flex_flows"
                },
                "flex_flows": [
                    {
                        "sid": "FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "account_sid": "ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "date_created": "2016-08-01T22:10:40Z",
                        "date_updated": "2016-08-01T22:10:40Z",
                        "friendly_name": "friendly_name",
                        "chat_service_sid": "SIaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "channel_type": "sms",
                        "contact_identity": "12345",
                        "enabled": true,
                        "integration_type": "studio",
                        "integration": {
                            "flow_sid": "FWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            "retry_count": 1
                        },
                        "long_lived": true,
                        "janitor_enabled": true,
                        "url": "https://flex-api.twilio.com/v1/FlexFlows/FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    }
                ]
            }
            '''
        ))

        actual = self.client.flex_api.v1.flex_flow.list()

        self.assertIsNotNone(actual)

    def test_read_empty_response(self):
        self.holodeck.mock(Response(
            200,
            '''
            {
                "meta": {
                    "page": 0,
                    "page_size": 50,
                    "first_page_url": "https://flex-api.twilio.com/v1/FlexFlows?PageSize=50&Page=0",
                    "previous_page_url": null,
                    "url": "https://flex-api.twilio.com/v1/FlexFlows?PageSize=50&Page=0",
                    "next_page_url": null,
                    "key": "flex_flows"
                },
                "flex_flows": []
            }
            '''
        ))

        actual = self.client.flex_api.v1.flex_flow.list()

        self.assertIsNotNone(actual)

    def test_fetch_request(self):
        self.holodeck.mock(Response(500, ''))

        with self.assertRaises(TwilioException):
            self.client.flex_api.v1.flex_flow("FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch()

        self.holodeck.assert_has_request(Request(
            'get',
            'https://flex-api.twilio.com/v1/FlexFlows/FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX',
        ))

    def test_fetch_response(self):
        self.holodeck.mock(Response(
            200,
            '''
            {
                "sid": "FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "account_sid": "ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "date_created": "2016-08-01T22:10:40Z",
                "date_updated": "2016-08-01T22:10:40Z",
                "friendly_name": "friendly_name",
                "chat_service_sid": "SIaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "channel_type": "sms",
                "contact_identity": "12345",
                "enabled": true,
                "integration_type": "studio",
                "integration": {
                    "flow_sid": "FWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "retry_count": 1
                },
                "long_lived": true,
                "janitor_enabled": true,
                "url": "https://flex-api.twilio.com/v1/FlexFlows/FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
            }
            '''
        ))

        actual = self.client.flex_api.v1.flex_flow("FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").fetch()

        self.assertIsNotNone(actual)

    def test_create_request(self):
        self.holodeck.mock(Response(500, ''))

        with self.assertRaises(TwilioException):
            self.client.flex_api.v1.flex_flow.create(friendly_name="friendly_name", chat_service_sid="ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", channel_type="web")

        values = {
            'FriendlyName': "friendly_name",
            'ChatServiceSid': "ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
            'ChannelType': "web",
        }

        self.holodeck.assert_has_request(Request(
            'post',
            'https://flex-api.twilio.com/v1/FlexFlows',
            data=values,
        ))

    def test_create_response(self):
        self.holodeck.mock(Response(
            201,
            '''
            {
                "sid": "FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "account_sid": "ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "date_created": "2016-08-01T22:10:40Z",
                "date_updated": "2016-08-01T22:10:40Z",
                "friendly_name": "friendly_name",
                "chat_service_sid": "SIaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "channel_type": "sms",
                "contact_identity": "12345",
                "enabled": true,
                "integration_type": "studio",
                "integration": {
                    "flow_sid": "FWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "retry_count": 1
                },
                "long_lived": true,
                "janitor_enabled": true,
                "url": "https://flex-api.twilio.com/v1/FlexFlows/FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
            }
            '''
        ))

        actual = self.client.flex_api.v1.flex_flow.create(friendly_name="friendly_name", chat_service_sid="ISXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", channel_type="web")

        self.assertIsNotNone(actual)

    def test_update_request(self):
        self.holodeck.mock(Response(500, ''))

        with self.assertRaises(TwilioException):
            self.client.flex_api.v1.flex_flow("FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update()

        self.holodeck.assert_has_request(Request(
            'post',
            'https://flex-api.twilio.com/v1/FlexFlows/FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX',
        ))

    def test_update_response(self):
        self.holodeck.mock(Response(
            200,
            '''
            {
                "sid": "FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "account_sid": "ACaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "date_created": "2016-08-01T22:10:40Z",
                "date_updated": "2016-08-01T22:10:40Z",
                "friendly_name": "friendly_name",
                "chat_service_sid": "SIaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "channel_type": "sms",
                "contact_identity": "12345",
                "enabled": true,
                "integration_type": "studio",
                "integration": {
                    "flow_sid": "FWaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    "retry_count": 1
                },
                "long_lived": true,
                "janitor_enabled": true,
                "url": "https://flex-api.twilio.com/v1/FlexFlows/FOaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
            }
            '''
        ))

        actual = self.client.flex_api.v1.flex_flow("FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").update()

        self.assertIsNotNone(actual)

    def test_delete_request(self):
        self.holodeck.mock(Response(500, ''))

        with self.assertRaises(TwilioException):
            self.client.flex_api.v1.flex_flow("FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete()

        self.holodeck.assert_has_request(Request(
            'delete',
            'https://flex-api.twilio.com/v1/FlexFlows/FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX',
        ))

    def test_delete_response(self):
        self.holodeck.mock(Response(
            204,
            None,
        ))

        actual = self.client.flex_api.v1.flex_flow("FOXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX").delete()

        self.assertTrue(actual)
