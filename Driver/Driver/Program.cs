using System;
using System.Text;
using System.Net.Sockets;
using System.Collections;

namespace Driver
{
    class Program
    {
        private static string hostName = System.Net.Dns.GetHostName();
        private static int port = 8000;

        private static Socket clientSocket;
        private static String input;

        private static int i = 0;

        static ArrayList list = new ArrayList();

        static void Main(string[] args)
        {
            Scanner scanner = new Scanner();
            setup();

            while (i < 10)
            {
                if (receiveFromMain().Equals("Ready"))
                {
                    Console.WriteLine("Scanning");
                    sendtoMain(scanner.scan());
                    i++;
                }
            }
            String time = scanner.avgTime().ToString();
            sendtoMain(time);

        }

        private static void setup()
        {
            clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            clientSocket.Connect(hostName, port);

        }

        private static void sendtoMain(String toSend)
        {
            int toSendLen = System.Text.Encoding.ASCII.GetByteCount(toSend);
            byte[] toSendBytes = System.Text.Encoding.ASCII.GetBytes(toSend);
            byte[] toSendLenBytes = System.BitConverter.GetBytes(toSendLen);
            clientSocket.Send(toSendLenBytes);
            clientSocket.Send(toSendBytes);
            Console.WriteLine("Message sent: " + toSend);
        }

        private static String receiveFromMain()
        {
            byte[] rcvLenBytes = new byte[4];
            clientSocket.Receive(rcvLenBytes);
            int rcvLen = System.BitConverter.ToInt32(rcvLenBytes, 0);
            byte[] rcvBytes = new byte[rcvLen];
            clientSocket.Receive(rcvBytes);
            String rcv = System.Text.Encoding.ASCII.GetString(rcvBytes);

            return rcv;
        }

        private static void close()
        {
            clientSocket.Close();
        }

    }
}
