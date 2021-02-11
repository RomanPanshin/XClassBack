# coding=utf-8
r"""
This code was generated by
\ / _    _  _|   _  _
 | (_)\/(_)(_|\/| |(/_  v1.0.0
      /       /
"""

from twilio.base import deserialize
from twilio.base import values
from twilio.base.instance_context import InstanceContext
from twilio.base.instance_resource import InstanceResource
from twilio.base.list_resource import ListResource
from twilio.base.page import Page
from twilio.rest.trunking.v1.trunk.credential_list import CredentialListList
from twilio.rest.trunking.v1.trunk.ip_access_control_list import IpAccessControlListList
from twilio.rest.trunking.v1.trunk.origination_url import OriginationUrlList
from twilio.rest.trunking.v1.trunk.phone_number import PhoneNumberList
from twilio.rest.trunking.v1.trunk.recording import RecordingList


class TrunkList(ListResource):

    def __init__(self, version):
        """
        Initialize the TrunkList

        :param Version version: Version that contains the resource

        :returns: twilio.rest.trunking.v1.trunk.TrunkList
        :rtype: twilio.rest.trunking.v1.trunk.TrunkList
        """
        super(TrunkList, self).__init__(version)

        # Path Solution
        self._solution = {}
        self._uri = '/Trunks'.format(**self._solution)

    def create(self, friendly_name=values.unset, domain_name=values.unset,
               disaster_recovery_url=values.unset,
               disaster_recovery_method=values.unset, transfer_mode=values.unset,
               secure=values.unset, cnam_lookup_enabled=values.unset):
        """
        Create the TrunkInstance

        :param unicode friendly_name: A string to describe the resource
        :param unicode domain_name: The unique address you reserve on Twilio to which you route your SIP traffic
        :param unicode disaster_recovery_url: The HTTP URL that we should call if an error occurs while sending SIP traffic towards your configured Origination URL
        :param unicode disaster_recovery_method: The HTTP method we should use to call the disaster_recovery_url
        :param TrunkInstance.TransferSetting transfer_mode: The call transfer settings for the trunk
        :param bool secure: Whether Secure Trunking is enabled for the trunk
        :param bool cnam_lookup_enabled: Whether Caller ID Name (CNAM) lookup should be enabled for the trunk

        :returns: The created TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkInstance
        """
        data = values.of({
            'FriendlyName': friendly_name,
            'DomainName': domain_name,
            'DisasterRecoveryUrl': disaster_recovery_url,
            'DisasterRecoveryMethod': disaster_recovery_method,
            'TransferMode': transfer_mode,
            'Secure': secure,
            'CnamLookupEnabled': cnam_lookup_enabled,
        })

        payload = self._version.create(method='POST', uri=self._uri, data=data, )

        return TrunkInstance(self._version, payload, )

    def stream(self, limit=None, page_size=None):
        """
        Streams TrunkInstance records from the API as a generator stream.
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
        :rtype: list[twilio.rest.trunking.v1.trunk.TrunkInstance]
        """
        limits = self._version.read_limits(limit, page_size)

        page = self.page(page_size=limits['page_size'], )

        return self._version.stream(page, limits['limit'])

    def list(self, limit=None, page_size=None):
        """
        Lists TrunkInstance records from the API as a list.
        Unlike stream(), this operation is eager and will load `limit` records into
        memory before returning.

        :param int limit: Upper limit for the number of records to return. list() guarantees
                          never to return more than limit.  Default is no limit
        :param int page_size: Number of records to fetch per request, when not set will use
                              the default value of 50 records.  If no page_size is defined
                              but a limit is defined, list() will attempt to read the limit
                              with the most efficient page size, i.e. min(limit, 1000)

        :returns: Generator that will yield up to limit results
        :rtype: list[twilio.rest.trunking.v1.trunk.TrunkInstance]
        """
        return list(self.stream(limit=limit, page_size=page_size, ))

    def page(self, page_token=values.unset, page_number=values.unset,
             page_size=values.unset):
        """
        Retrieve a single page of TrunkInstance records from the API.
        Request is executed immediately

        :param str page_token: PageToken provided by the API
        :param int page_number: Page Number, this value is simply for client state
        :param int page_size: Number of records to return, defaults to 50

        :returns: Page of TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkPage
        """
        data = values.of({'PageToken': page_token, 'Page': page_number, 'PageSize': page_size, })

        response = self._version.page(method='GET', uri=self._uri, params=data, )

        return TrunkPage(self._version, response, self._solution)

    def get_page(self, target_url):
        """
        Retrieve a specific page of TrunkInstance records from the API.
        Request is executed immediately

        :param str target_url: API-generated URL for the requested results page

        :returns: Page of TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkPage
        """
        response = self._version.domain.twilio.request(
            'GET',
            target_url,
        )

        return TrunkPage(self._version, response, self._solution)

    def get(self, sid):
        """
        Constructs a TrunkContext

        :param sid: The unique string that identifies the resource

        :returns: twilio.rest.trunking.v1.trunk.TrunkContext
        :rtype: twilio.rest.trunking.v1.trunk.TrunkContext
        """
        return TrunkContext(self._version, sid=sid, )

    def __call__(self, sid):
        """
        Constructs a TrunkContext

        :param sid: The unique string that identifies the resource

        :returns: twilio.rest.trunking.v1.trunk.TrunkContext
        :rtype: twilio.rest.trunking.v1.trunk.TrunkContext
        """
        return TrunkContext(self._version, sid=sid, )

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Trunking.V1.TrunkList>'


class TrunkPage(Page):

    def __init__(self, version, response, solution):
        """
        Initialize the TrunkPage

        :param Version version: Version that contains the resource
        :param Response response: Response from the API

        :returns: twilio.rest.trunking.v1.trunk.TrunkPage
        :rtype: twilio.rest.trunking.v1.trunk.TrunkPage
        """
        super(TrunkPage, self).__init__(version, response)

        # Path Solution
        self._solution = solution

    def get_instance(self, payload):
        """
        Build an instance of TrunkInstance

        :param dict payload: Payload response from the API

        :returns: twilio.rest.trunking.v1.trunk.TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkInstance
        """
        return TrunkInstance(self._version, payload, )

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        return '<Twilio.Trunking.V1.TrunkPage>'


class TrunkContext(InstanceContext):

    def __init__(self, version, sid):
        """
        Initialize the TrunkContext

        :param Version version: Version that contains the resource
        :param sid: The unique string that identifies the resource

        :returns: twilio.rest.trunking.v1.trunk.TrunkContext
        :rtype: twilio.rest.trunking.v1.trunk.TrunkContext
        """
        super(TrunkContext, self).__init__(version)

        # Path Solution
        self._solution = {'sid': sid, }
        self._uri = '/Trunks/{sid}'.format(**self._solution)

        # Dependents
        self._origination_urls = None
        self._credentials_lists = None
        self._ip_access_control_lists = None
        self._phone_numbers = None
        self._recordings = None

    def fetch(self):
        """
        Fetch the TrunkInstance

        :returns: The fetched TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkInstance
        """
        payload = self._version.fetch(method='GET', uri=self._uri, )

        return TrunkInstance(self._version, payload, sid=self._solution['sid'], )

    def delete(self):
        """
        Deletes the TrunkInstance

        :returns: True if delete succeeds, False otherwise
        :rtype: bool
        """
        return self._version.delete(method='DELETE', uri=self._uri, )

    def update(self, friendly_name=values.unset, domain_name=values.unset,
               disaster_recovery_url=values.unset,
               disaster_recovery_method=values.unset, transfer_mode=values.unset,
               secure=values.unset, cnam_lookup_enabled=values.unset):
        """
        Update the TrunkInstance

        :param unicode friendly_name: A string to describe the resource
        :param unicode domain_name: The unique address you reserve on Twilio to which you route your SIP traffic
        :param unicode disaster_recovery_url: The HTTP URL that we should call if an error occurs while sending SIP traffic towards your configured Origination URL
        :param unicode disaster_recovery_method: The HTTP method we should use to call the disaster_recovery_url
        :param TrunkInstance.TransferSetting transfer_mode: The call transfer settings for the trunk
        :param bool secure: Whether Secure Trunking is enabled for the trunk
        :param bool cnam_lookup_enabled: Whether Caller ID Name (CNAM) lookup should be enabled for the trunk

        :returns: The updated TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkInstance
        """
        data = values.of({
            'FriendlyName': friendly_name,
            'DomainName': domain_name,
            'DisasterRecoveryUrl': disaster_recovery_url,
            'DisasterRecoveryMethod': disaster_recovery_method,
            'TransferMode': transfer_mode,
            'Secure': secure,
            'CnamLookupEnabled': cnam_lookup_enabled,
        })

        payload = self._version.update(method='POST', uri=self._uri, data=data, )

        return TrunkInstance(self._version, payload, sid=self._solution['sid'], )

    @property
    def origination_urls(self):
        """
        Access the origination_urls

        :returns: twilio.rest.trunking.v1.trunk.origination_url.OriginationUrlList
        :rtype: twilio.rest.trunking.v1.trunk.origination_url.OriginationUrlList
        """
        if self._origination_urls is None:
            self._origination_urls = OriginationUrlList(self._version, trunk_sid=self._solution['sid'], )
        return self._origination_urls

    @property
    def credentials_lists(self):
        """
        Access the credentials_lists

        :returns: twilio.rest.trunking.v1.trunk.credential_list.CredentialListList
        :rtype: twilio.rest.trunking.v1.trunk.credential_list.CredentialListList
        """
        if self._credentials_lists is None:
            self._credentials_lists = CredentialListList(self._version, trunk_sid=self._solution['sid'], )
        return self._credentials_lists

    @property
    def ip_access_control_lists(self):
        """
        Access the ip_access_control_lists

        :returns: twilio.rest.trunking.v1.trunk.ip_access_control_list.IpAccessControlListList
        :rtype: twilio.rest.trunking.v1.trunk.ip_access_control_list.IpAccessControlListList
        """
        if self._ip_access_control_lists is None:
            self._ip_access_control_lists = IpAccessControlListList(
                self._version,
                trunk_sid=self._solution['sid'],
            )
        return self._ip_access_control_lists

    @property
    def phone_numbers(self):
        """
        Access the phone_numbers

        :returns: twilio.rest.trunking.v1.trunk.phone_number.PhoneNumberList
        :rtype: twilio.rest.trunking.v1.trunk.phone_number.PhoneNumberList
        """
        if self._phone_numbers is None:
            self._phone_numbers = PhoneNumberList(self._version, trunk_sid=self._solution['sid'], )
        return self._phone_numbers

    @property
    def recordings(self):
        """
        Access the recordings

        :returns: twilio.rest.trunking.v1.trunk.recording.RecordingList
        :rtype: twilio.rest.trunking.v1.trunk.recording.RecordingList
        """
        if self._recordings is None:
            self._recordings = RecordingList(self._version, trunk_sid=self._solution['sid'], )
        return self._recordings

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        context = ' '.join('{}={}'.format(k, v) for k, v in self._solution.items())
        return '<Twilio.Trunking.V1.TrunkContext {}>'.format(context)


class TrunkInstance(InstanceResource):

    class TransferSetting(object):
        DISABLE_ALL = "disable-all"
        ENABLE_ALL = "enable-all"
        SIP_ONLY = "sip-only"

    def __init__(self, version, payload, sid=None):
        """
        Initialize the TrunkInstance

        :returns: twilio.rest.trunking.v1.trunk.TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkInstance
        """
        super(TrunkInstance, self).__init__(version)

        # Marshaled Properties
        self._properties = {
            'account_sid': payload.get('account_sid'),
            'domain_name': payload.get('domain_name'),
            'disaster_recovery_method': payload.get('disaster_recovery_method'),
            'disaster_recovery_url': payload.get('disaster_recovery_url'),
            'friendly_name': payload.get('friendly_name'),
            'secure': payload.get('secure'),
            'recording': payload.get('recording'),
            'transfer_mode': payload.get('transfer_mode'),
            'cnam_lookup_enabled': payload.get('cnam_lookup_enabled'),
            'auth_type': payload.get('auth_type'),
            'auth_type_set': payload.get('auth_type_set'),
            'date_created': deserialize.iso8601_datetime(payload.get('date_created')),
            'date_updated': deserialize.iso8601_datetime(payload.get('date_updated')),
            'sid': payload.get('sid'),
            'url': payload.get('url'),
            'links': payload.get('links'),
        }

        # Context
        self._context = None
        self._solution = {'sid': sid or self._properties['sid'], }

    @property
    def _proxy(self):
        """
        Generate an instance context for the instance, the context is capable of
        performing various actions.  All instance actions are proxied to the context

        :returns: TrunkContext for this TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkContext
        """
        if self._context is None:
            self._context = TrunkContext(self._version, sid=self._solution['sid'], )
        return self._context

    @property
    def account_sid(self):
        """
        :returns: The SID of the Account that created the resource
        :rtype: unicode
        """
        return self._properties['account_sid']

    @property
    def domain_name(self):
        """
        :returns: The unique address you reserve on Twilio to which you route your SIP traffic
        :rtype: unicode
        """
        return self._properties['domain_name']

    @property
    def disaster_recovery_method(self):
        """
        :returns: The HTTP method we use to call the disaster_recovery_url
        :rtype: unicode
        """
        return self._properties['disaster_recovery_method']

    @property
    def disaster_recovery_url(self):
        """
        :returns: The HTTP URL that we call if an error occurs while sending SIP traffic towards your configured Origination URL
        :rtype: unicode
        """
        return self._properties['disaster_recovery_url']

    @property
    def friendly_name(self):
        """
        :returns: The string that you assigned to describe the resource
        :rtype: unicode
        """
        return self._properties['friendly_name']

    @property
    def secure(self):
        """
        :returns: Whether Secure Trunking is enabled for the trunk
        :rtype: bool
        """
        return self._properties['secure']

    @property
    def recording(self):
        """
        :returns: The recording settings for the trunk
        :rtype: dict
        """
        return self._properties['recording']

    @property
    def transfer_mode(self):
        """
        :returns: The call transfer settings for the trunk
        :rtype: TrunkInstance.TransferSetting
        """
        return self._properties['transfer_mode']

    @property
    def cnam_lookup_enabled(self):
        """
        :returns: Whether Caller ID Name (CNAM) lookup is enabled for the trunk
        :rtype: bool
        """
        return self._properties['cnam_lookup_enabled']

    @property
    def auth_type(self):
        """
        :returns: The types of authentication mapped to the domain
        :rtype: unicode
        """
        return self._properties['auth_type']

    @property
    def auth_type_set(self):
        """
        :returns: Reserved
        :rtype: list[unicode]
        """
        return self._properties['auth_type_set']

    @property
    def date_created(self):
        """
        :returns: The RFC 2822 date and time in GMT when the resource was created
        :rtype: datetime
        """
        return self._properties['date_created']

    @property
    def date_updated(self):
        """
        :returns: The RFC 2822 date and time in GMT when the resource was last updated
        :rtype: datetime
        """
        return self._properties['date_updated']

    @property
    def sid(self):
        """
        :returns: The unique string that identifies the resource
        :rtype: unicode
        """
        return self._properties['sid']

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
        :returns: The URLs of related resources
        :rtype: unicode
        """
        return self._properties['links']

    def fetch(self):
        """
        Fetch the TrunkInstance

        :returns: The fetched TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkInstance
        """
        return self._proxy.fetch()

    def delete(self):
        """
        Deletes the TrunkInstance

        :returns: True if delete succeeds, False otherwise
        :rtype: bool
        """
        return self._proxy.delete()

    def update(self, friendly_name=values.unset, domain_name=values.unset,
               disaster_recovery_url=values.unset,
               disaster_recovery_method=values.unset, transfer_mode=values.unset,
               secure=values.unset, cnam_lookup_enabled=values.unset):
        """
        Update the TrunkInstance

        :param unicode friendly_name: A string to describe the resource
        :param unicode domain_name: The unique address you reserve on Twilio to which you route your SIP traffic
        :param unicode disaster_recovery_url: The HTTP URL that we should call if an error occurs while sending SIP traffic towards your configured Origination URL
        :param unicode disaster_recovery_method: The HTTP method we should use to call the disaster_recovery_url
        :param TrunkInstance.TransferSetting transfer_mode: The call transfer settings for the trunk
        :param bool secure: Whether Secure Trunking is enabled for the trunk
        :param bool cnam_lookup_enabled: Whether Caller ID Name (CNAM) lookup should be enabled for the trunk

        :returns: The updated TrunkInstance
        :rtype: twilio.rest.trunking.v1.trunk.TrunkInstance
        """
        return self._proxy.update(
            friendly_name=friendly_name,
            domain_name=domain_name,
            disaster_recovery_url=disaster_recovery_url,
            disaster_recovery_method=disaster_recovery_method,
            transfer_mode=transfer_mode,
            secure=secure,
            cnam_lookup_enabled=cnam_lookup_enabled,
        )

    @property
    def origination_urls(self):
        """
        Access the origination_urls

        :returns: twilio.rest.trunking.v1.trunk.origination_url.OriginationUrlList
        :rtype: twilio.rest.trunking.v1.trunk.origination_url.OriginationUrlList
        """
        return self._proxy.origination_urls

    @property
    def credentials_lists(self):
        """
        Access the credentials_lists

        :returns: twilio.rest.trunking.v1.trunk.credential_list.CredentialListList
        :rtype: twilio.rest.trunking.v1.trunk.credential_list.CredentialListList
        """
        return self._proxy.credentials_lists

    @property
    def ip_access_control_lists(self):
        """
        Access the ip_access_control_lists

        :returns: twilio.rest.trunking.v1.trunk.ip_access_control_list.IpAccessControlListList
        :rtype: twilio.rest.trunking.v1.trunk.ip_access_control_list.IpAccessControlListList
        """
        return self._proxy.ip_access_control_lists

    @property
    def phone_numbers(self):
        """
        Access the phone_numbers

        :returns: twilio.rest.trunking.v1.trunk.phone_number.PhoneNumberList
        :rtype: twilio.rest.trunking.v1.trunk.phone_number.PhoneNumberList
        """
        return self._proxy.phone_numbers

    @property
    def recordings(self):
        """
        Access the recordings

        :returns: twilio.rest.trunking.v1.trunk.recording.RecordingList
        :rtype: twilio.rest.trunking.v1.trunk.recording.RecordingList
        """
        return self._proxy.recordings

    def __repr__(self):
        """
        Provide a friendly representation

        :returns: Machine friendly representation
        :rtype: str
        """
        context = ' '.join('{}={}'.format(k, v) for k, v in self._solution.items())
        return '<Twilio.Trunking.V1.TrunkInstance {}>'.format(context)
