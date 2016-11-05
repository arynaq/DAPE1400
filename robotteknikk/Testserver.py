from twisted.internet import protocol, reactor, endpoints
import time


class TCPTestServer(protocol.Protocol):

    def __init__(self):
        print "Intializing server...."

    def dataReceived(self, data):
        print "Received data:"
        print data;
        self.transport.write("Random\n")
        time.sleep(1)
        self.transport.write("kek\n")
        time.sleep(1)
        self.transport.write("ok\n")

    def connectionMade(self):
        print "Connection made..."
        self.transport.setTcpNoDelay(True)


class ServerFactory(protocol.Factory):

    def __init__(self):
        self.server =  TCPTestServer()

    def buildProtocol(self, addr):
        return self.server


endpoints.serverFromString(reactor, "tcp:27000").listen(ServerFactory())
reactor.run()
