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


class RecordingTestCase(IntegrationTestCase):

    def test_fetch_request(self):
        self.holodeck.mock(Response(500, ''))

        with self.assertRaises(TwilioException):
            self.client.trunking.v1.trunks("TKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX") \
                                   .recordings().fetch()

        self.holodeck.assert_has_request(Request(
            'get',
            'https://trunking.twilio.com/v1/Trunks/TKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Recording',
        ))

    def test_fetch_response(self):
        self.holodeck.mock(Response(
            200,
            '''
            {
                "mode": "do-not-record",
                "trim": "do-not-trim"
            }
            '''
        ))

        actual = self.client.trunking.v1.trunks("TKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX") \
                                        .recordings().fetch()

        self.assertIsNotNone(actual)

    def test_update_request(self):
        self.holodeck.mock(Response(500, ''))

        with self.assertRaises(TwilioException):
            self.client.trunking.v1.trunks("TKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX") \
                                   .recordings().update()

        self.holodeck.assert_has_request(Request(
            'post',
            'https://trunking.twilio.com/v1/Trunks/TKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Recording',
        ))

    def test_update_response(self):
        self.holodeck.mock(Response(
            200,
            '''
            {
                "mode": "do-not-record",
                "trim": "do-not-trim"
            }
            '''
        ))

        actual = self.client.trunking.v1.trunks("TKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX") \
                                        .recordings().update()

        self.assertIsNotNone(actual)
