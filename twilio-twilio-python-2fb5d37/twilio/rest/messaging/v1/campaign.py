# coding=utf-8
r"""
This code was generated by
\ / _    _  _|   _  _
 | (_)\/(_)(_|\/| |(/_  v1.0.0
      /       /
"""

from twilio.base import deserialize
from twilio.base import serialize
from twilio.base import values
from twilio.base.instance_context import InstanceContext
from twilio.base.instance_resource import InstanceResource
from twilio.base.list_resource import ListResource
from twilio.base.page import Page


class CampaignList(ListResource):
    """ PLEASE NOTE that this class contains beta products that are subject to
    change. Use them with caution. """

    def __init__(self, version):
        """
        Initialize the CampaignList

        :param Version version: Version that contains the resource

        :returns: twilio.rest.messaging.v1.campaign.CampaignList
        :rtype: twilio.rest.messaging.v1.campaign.CampaignList
        """
        super(CampaignList, self).__init__(version)

        # Path Solution
        self._solution = {}
        self._uri = '/a2p/Campaigns'.format(**self._solution)

    def stream(self, limit=None, page_size=None):
        """
        Streams CampaignInstance records from the API as a generator stream.
        This operation lazily loads records as efficiently as possible until the limit
        is reached.
        The results are returned as a generator, so this operation is memory efficient.

        :param int limit: Upper limit for the number of records to return. stream()
                          guarantees to never return more than limit.  Default is no limit
        :param int page_size: Number of records to fetch per request, when not set will use
                              the default value of 50 records.  If no page_size is defined
                              but a limit is defined, stream() will attempt to read the
                              limit with the most efficient page size, i.e. min(limit, 1000)

        :returns: Generator that will yield up to limit results
        :rtype: list[twilio.rest.messaging.v1.campaign.CampaignInstance]
        """
        limits = self._version.read_limits(limit, page_size)

        page = self.page(page_size=limits['page_size'], )

        return self._version.stream(page, limits['limit'])

    def list(self, limit=None, page_size=None):
        """
        Lists CampaignInstance records from the API as a list.
        Unlike stream(), this operation is eager and will load `limit` records into
        memory before returning.

        :param int limit: Upper limit for the number of records to return. list() guarantees
                          never to return more than limit.  Default is no limit
        :param int page_size: Number of records to fetch per request, when not set will use
                              the default value of 50 records.  If no page_size is defined
                              but a limit is defined, list() will attempt to read the limit
                              with the most efficient page size, i.e. min(limit, 1000)

        :returns: Generator that will yield up to limit results
        :rtype: list[twilio.rest.messaging.v1.campaign.CampaignInstance]
        """
        return list(self.stream(limit=limit, page_size=page_size, ))

    def page(self, page_token=values.unset, page_number=values.unset,
             page_size=values.unset):
        """
        Retrieve a single page of CampaignInstance records from the API.
        Request is executed immediately

        :param str page_token: PageToken provided by the API
        :param int page_number: Page Number, this value is simply for client state
        :param int page_size: Number of records to return, defaults to 50

        :returns: Page of CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignPage
        """
        data = values.of({'PageToken': page_token, 'Page': page_number, 'PageSize': page_size, })

        response = self._version.page(method='GET', uri=self._uri, params=data, )

        return CampaignPage(self._version, response, self._solution)

    def get_page(self, target_url):
        """
        Retrieve a specific page of CampaignInstance records from the API.
        Request is executed immediately

        :param str target_url: API-generated URL for the requested results page

        :returns: Page of CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignPage
        """
        response = self._version.domain.twilio.request(
            'GET',
            target_url,
        )

        return CampaignPage(self._version, response, self._solution)

    def create(self, brand_registration_sid, use_case, description, message_samples,
               has_embedded_links, has_embedded_phone, messaging_service_sid):
        """
        Create the CampaignInstance

        :param unicode brand_registration_sid: A2P BrandRegistration Sid
        :param unicode use_case: A2P Campaign UseCase.
        :param unicode description: A short description of what this SMS campaign does
        :param list[unicode] message_samples: Message samples
        :param bool has_embedded_links: Indicate that this SMS campaign will send messages that contain links
        :param bool has_embedded_phone: Indicates that this SMS campaign will send messages that contain phone numbers
        :param unicode messaging_service_sid: MessagingService SID

        :returns: The created CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignInstance
        """
        data = values.of({
            'BrandRegistrationSid': brand_registration_sid,
            'UseCase': use_case,
            'Description': description,
            'MessageSamples': serialize.map(message_samples, lambda e: e),
            'HasEmbeddedLinks': has_embedded_links,
            'HasEmbeddedPhone': has_embedded_phone,
            'MessagingServiceSid': messaging_service_sid,
        })

        payload = self._version.create(method='POST', uri=self._uri, data=data, )

        return CampaignInstance(self._version, payload, )

    def get(self, sid):
        """
        Constructs a CampaignContext

        :param sid: The SID that identifies the resource to fetch

        :returns: twilio.rest.messaging.v1.campaign.CampaignContext
        :rtype: twilio.rest.messaging.v1.campaign.CampaignContext
        """
        return CampaignContext(self._version, sid=sid, )

    def __call__(self, sid):
        """
        Constructs a CampaignContext

        :param sid: The SID that identifies the resource to fetch

        :returns: twilio.rest.messaging.v1.campaign.CampaignContext
        :rtype: twilio.rest.messaging.v1.campaign.CampaignContext
        """
        return CampaignContext(self._version, sid=sid, )

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Messaging.V1.CampaignList>'


class CampaignPage(Page):
    """ PLEASE NOTE that this class contains beta products that are subject to
    change. Use them with caution. """

    def __init__(self, version, response, solution):
        """
        Initialize the CampaignPage

        :param Version version: Version that contains the resource
        :param Response response: Response from the API

        :returns: twilio.rest.messaging.v1.campaign.CampaignPage
        :rtype: twilio.rest.messaging.v1.campaign.CampaignPage
        """
        super(CampaignPage, self).__init__(version, response)

        # Path Solution
        self._solution = solution

    def get_instance(self, payload):
        """
        Build an instance of CampaignInstance

        :param dict payload: Payload response from the API

        :returns: twilio.rest.messaging.v1.campaign.CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignInstance
        """
        return CampaignInstance(self._version, payload, )

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Messaging.V1.CampaignPage>'


class CampaignContext(InstanceContext):
    """ PLEASE NOTE that this class contains beta products that are subject to
    change. Use them with caution. """

    def __init__(self, version, sid):
        """
        Initialize the CampaignContext

        :param Version version: Version that contains the resource
        :param sid: The SID that identifies the resource to fetch

        :returns: twilio.rest.messaging.v1.campaign.CampaignContext
        :rtype: twilio.rest.messaging.v1.campaign.CampaignContext
        """
        super(CampaignContext, self).__init__(version)

        # Path Solution
        self._solution = {'sid': sid, }
        self._uri = '/a2p/Campaigns/{sid}'.format(**self._solution)

    def fetch(self):
        """
        Fetch the CampaignInstance

        :returns: The fetched CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignInstance
        """
        payload = self._version.fetch(method='GET', uri=self._uri, )

        return CampaignInstance(self._version, payload, sid=self._solution['sid'], )

    def delete(self):
        """
        Deletes the CampaignInstance

        :returns: True if delete succeeds, False otherwise
        :rtype: bool
        """
        return self._version.delete(method='DELETE', uri=self._uri, )

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        context = ' '.join('{}={}'.format(k, v) for k, v in self._solution.items())
        return '<Twilio.Messaging.V1.CampaignContext {}>'.format(context)


class CampaignInstance(InstanceResource):
    """ PLEASE NOTE that this class contains beta products that are subject to
    change. Use them with caution. """

    class Status(object):
        APPROVED = "approved"
        PENDING = "pending"
        FAILED = "failed"

    def __init__(self, version, payload, sid=None):
        """
        Initialize the CampaignInstance

        :returns: twilio.rest.messaging.v1.campaign.CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignInstance
        """
        super(CampaignInstance, self).__init__(version)

        # Marshaled Properties
        self._properties = {
            'account_sid': payload.get('account_sid'),
            'messaging_service_sid': payload.get('messaging_service_sid'),
            'brand_registration_sid': payload.get('brand_registration_sid'),
            'sid': payload.get('sid'),
            'date_created': deserialize.iso8601_datetime(payload.get('date_created')),
            'date_updated': deserialize.iso8601_datetime(payload.get('date_updated')),
            'description': payload.get('description'),
            'message_samples': payload.get('message_samples'),
            'status': payload.get('status'),
            'failure_reason': payload.get('failure_reason'),
            'use_case': payload.get('use_case'),
            'has_embedded_links': payload.get('has_embedded_links'),
            'has_embedded_phone': payload.get('has_embedded_phone'),
            'url': payload.get('url'),
        }

        # Context
        self._context = None
        self._solution = {'sid': sid or self._properties['sid'], }

    @property
    def _proxy(self):
        """
        Generate an instance context for the instance, the context is capable of
        performing various actions.  All instance actions are proxied to the context

        :returns: CampaignContext for this CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignContext
        """
        if self._context is None:
            self._context = CampaignContext(self._version, sid=self._solution['sid'], )
        return self._context

    @property
    def account_sid(self):
        """
        :returns: The SID of the Account that created the resource
        :rtype: unicode
        """
        return self._properties['account_sid']

    @property
    def messaging_service_sid(self):
        """
        :returns: MessagingService SID
        :rtype: unicode
        """
        return self._properties['messaging_service_sid']

    @property
    def brand_registration_sid(self):
        """
        :returns: A2P BrandRegistration Sid
        :rtype: unicode
        """
        return self._properties['brand_registration_sid']

    @property
    def sid(self):
        """
        :returns: Campaign sid
        :rtype: unicode
        """
        return self._properties['sid']

    @property
    def date_created(self):
        """
        :returns: The ISO 8601 date and time in GMT when the resource was created
        :rtype: datetime
        """
        return self._properties['date_created']

    @property
    def date_updated(self):
        """
        :returns: The ISO 8601 date and time in GMT when the resource was last updated
        :rtype: datetime
        """
        return self._properties['date_updated']

    @property
    def description(self):
        """
        :returns: A short description of what this SMS campaign does
        :rtype: unicode
        """
        return self._properties['description']

    @property
    def message_samples(self):
        """
        :returns: Message samples
        :rtype: list[unicode]
        """
        return self._properties['message_samples']

    @property
    def status(self):
        """
        :returns: Campaign status
        :rtype: CampaignInstance.Status
        """
        return self._properties['status']

    @property
    def failure_reason(self):
        """
        :returns: A reason why campaign registration has failed
        :rtype: unicode
        """
        return self._properties['failure_reason']

    @property
    def use_case(self):
        """
        :returns: A2P Campaign UseCase.
        :rtype: unicode
        """
        return self._properties['use_case']

    @property
    def has_embedded_links(self):
        """
        :returns: Indicate that this SMS campaign will send messages that contain links
        :rtype: bool
        """
        return self._properties['has_embedded_links']

    @property
    def has_embedded_phone(self):
        """
        :returns: Indicates that this SMS campaign will send messages that contain phone numbers
        :rtype: bool
        """
        return self._properties['has_embedded_phone']

    @property
    def url(self):
        """
        :returns: The absolute URL of the Campaign resource
        :rtype: unicode
        """
        return self._properties['url']

    def fetch(self):
        """
        Fetch the CampaignInstance

        :returns: The fetched CampaignInstance
        :rtype: twilio.rest.messaging.v1.campaign.CampaignInstance
        """
        return self._proxy.fetch()

    def delete(self):
        """
        Deletes the CampaignInstance

        :returns: True if delete succeeds, False otherwise
        :rtype: bool
        """
        return self._proxy.delete()

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        context = ' '.join('{}={}'.format(k, v) for k, v in self._solution.items())
        return '<Twilio.Messaging.V1.CampaignInstance {}>'.format(context)
