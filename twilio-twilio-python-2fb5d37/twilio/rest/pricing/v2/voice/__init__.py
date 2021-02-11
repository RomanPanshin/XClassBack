# coding=utf-8
r"""
This code was generated by
\ / _    _  _|   _  _
 | (_)\/(_)(_|\/| |(/_  v1.0.0
      /       /
"""

from twilio.base.instance_resource import InstanceResource
from twilio.base.list_resource import ListResource
from twilio.base.page import Page
from twilio.rest.pricing.v2.voice.country import CountryList
from twilio.rest.pricing.v2.voice.number import NumberList


class VoiceList(ListResource):

    def __init__(self, version):
        """
        Initialize the VoiceList

        :param Version version: Version that contains the resource

        :returns: twilio.rest.pricing.v2.voice.VoiceList
        :rtype: twilio.rest.pricing.v2.voice.VoiceList
        """
        super(VoiceList, self).__init__(version)

        # Path Solution
        self._solution = {}

        # Components
        self._countries = None
        self._numbers = None

    @property
    def countries(self):
        """
        Access the countries

        :returns: twilio.rest.pricing.v2.voice.country.CountryList
        :rtype: twilio.rest.pricing.v2.voice.country.CountryList
        """
        if self._countries is None:
            self._countries = CountryList(self._version, )
        return self._countries

    @property
    def numbers(self):
        """
        Access the numbers

        :returns: twilio.rest.pricing.v2.voice.number.NumberList
        :rtype: twilio.rest.pricing.v2.voice.number.NumberList
        """
        if self._numbers is None:
            self._numbers = NumberList(self._version, )
        return self._numbers

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Pricing.V2.VoiceList>'


class VoicePage(Page):

    def __init__(self, version, response, solution):
        """
        Initialize the VoicePage

        :param Version version: Version that contains the resource
        :param Response response: Response from the API

        :returns: twilio.rest.pricing.v2.voice.VoicePage
        :rtype: twilio.rest.pricing.v2.voice.VoicePage
        """
        super(VoicePage, self).__init__(version, response)

        # Path Solution
        self._solution = solution

    def get_instance(self, payload):
        """
        Build an instance of VoiceInstance

        :param dict payload: Payload response from the API

        :returns: twilio.rest.pricing.v2.voice.VoiceInstance
        :rtype: twilio.rest.pricing.v2.voice.VoiceInstance
        """
        return VoiceInstance(self._version, payload, )

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Pricing.V2.VoicePage>'


class VoiceInstance(InstanceResource):

    def __init__(self, version, payload):
        """
        Initialize the VoiceInstance

        :returns: twilio.rest.pricing.v2.voice.VoiceInstance
        :rtype: twilio.rest.pricing.v2.voice.VoiceInstance
        """
        super(VoiceInstance, self).__init__(version)

        # Marshaled Properties
        self._properties = {
            'name': payload.get('name'),
            'url': payload.get('url'),
            'links': payload.get('links'),
        }

        # Context
        self._context = None
        self._solution = {}

    @property
    def name(self):
        """
        :returns: The resource name
        :rtype: unicode
        """
        return self._properties['name']

    @property
    def url(self):
        """
        :returns: The absolute URL of the resource
        :rtype: unicode
        """
        return self._properties['url']

    @property
    def links(self):
        """
        :returns: The URLs of the related Countries and Numbers resources
        :rtype: unicode
        """
        return self._properties['links']

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Pricing.V2.VoiceInstance>'
