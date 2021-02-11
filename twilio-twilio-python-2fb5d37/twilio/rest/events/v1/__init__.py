# coding=utf-8
r"""
This code was generated by
\ / _    _  _|   _  _
 | (_)\/(_)(_|\/| |(/_  v1.0.0
      /       /
"""

from twilio.base.version import Version
from twilio.rest.events.v1.event_type import EventTypeList
from twilio.rest.events.v1.schema import SchemaList
from twilio.rest.events.v1.sink import SinkList
from twilio.rest.events.v1.subscription import SubscriptionList


class V1(Version):

    def __init__(self, domain):
        """
        Initialize the V1 version of Events

        :returns: V1 version of Events
        :rtype: twilio.rest.events.v1.V1.V1
        """
        super(V1, self).__init__(domain)
        self.version = 'v1'
        self._event_types = None
        self._schemas = None
        self._sinks = None
        self._subscriptions = None

    @property
    def event_types(self):
        """
        :rtype: twilio.rest.events.v1.event_type.EventTypeList
        """
        if self._event_types is None:
            self._event_types = EventTypeList(self)
        return self._event_types

    @property
    def schemas(self):
        """
        :rtype: twilio.rest.events.v1.schema.SchemaList
        """
        if self._schemas is None:
            self._schemas = SchemaList(self)
        return self._schemas

    @property
    def sinks(self):
        """
        :rtype: twilio.rest.events.v1.sink.SinkList
        """
        if self._sinks is None:
            self._sinks = SinkList(self)
        return self._sinks

    @property
    def subscriptions(self):
        """
        :rtype: twilio.rest.events.v1.subscription.SubscriptionList
        """
        if self._subscriptions is None:
            self._subscriptions = SubscriptionList(self)
        return self._subscriptions

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Events.V1>'