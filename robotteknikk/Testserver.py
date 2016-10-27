from twisted.internet import protocol, reactor, endpoints


class TCPTestServer(protocol.Protocol):

    def __init__(self):
        print "Intializing server...."

    def dataReceived(self, data):
        print "Received data:"
        print data;



class ServerFactory(protocol.Factory):

    def __init__(self):
        self.server =  TCPTestServer()

    def buildProtocol(self, addr):
        return self.server


endpoints.serverFromString(reactor, "tcp:27000").listen(ServerFactory())
reactor.run()
