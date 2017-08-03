using System;
using System.Collections;
using CoreScanner;
using System.Xml;
using System.Text;

namespace Driver
{
    internal class Scanner
    {
        //Setup variables
        static CCoreScanner coreScanner;
        static string barcode;
        static Boolean barcodeIsRead = false;

        //Analysis 
        private ArrayList times = new ArrayList();

        //Constructor
        public Scanner()
        {
            coreScanner = new CoreScanner.CCoreScanner();
            open();
            subscribeBarcode();

        }

        //Open API
        public void open()
        {
            short[] scannerTypes = new short[1];
            scannerTypes[0] = 1;
            short numScanTypes = 1;
            int status;

            coreScanner.Open(0, scannerTypes, numScanTypes, out status);

        }

        //Subscribe to Barcode events
        public void subscribeBarcode()
        {
            coreScanner.BarcodeEvent += new _ICoreScannerEvents_BarcodeEventEventHandler(OnBarcodeEvent);

        }

        //Print Barcode Scan Data
        public void readScan()
        {

            int opCode = 1001;
            string outXML;
            int status;
            string inXML = "<inArgs>" +
                                "<cmdArgs>" +
                                    "<arg-int>1</arg-int>" + // Number of events you want to subscribe
                                    "<arg-int>1</arg-int>" + // Comma separated event IDs
                                "</cmdArgs>" +
                            "</inArgs>";

            coreScanner.ExecCommand(opCode, ref inXML, out outXML, out status);
        }

        public void OnBarcodeEvent(short eventType, ref string pscanData)
        {
            barcode = pscanData;
            barcodeIsRead = true;
        }


        //Soft Trigger Pull
        public void pullTrigger()
        {
            int opCode = 2011; //DEVICE_PULL_TRIGGER value
            int status;
            string outXML;
            string inXML = "<inArgs>" +
                               "<scannerID >1</scannerID>" +
                           "</inArgs>";
            coreScanner.ExecCommand(opCode, ref inXML, out outXML, out status);
        }

        //Soft Trigger Release
        public void releaseTrigger()
        {
            int opCode = 2012; //DEVICE_RELEASE_TRIGGER value
            int status;
            string outXML;
            string inXML = "<inArgs>" +
                               "<scannerID >1</scannerID>" +
                           "</inArgs>";
            coreScanner.ExecCommand(opCode, ref inXML, out outXML, out status);
        }

        //Decode raw data
        private String getDataLabel(String xml)
        {
            XmlDocument xmlDoc = new XmlDocument();
            xmlDoc.LoadXml(xml);
            string tmp = xmlDoc.DocumentElement.GetElementsByTagName("datalabel").Item(0).InnerText;
            return tmp;

        }

        private String removeSpaces(String input)
        {

            StringBuilder returnStr = new StringBuilder();
            for (int i = 0; i <= input.Length - 1; i += 1)
            {
                String tmp = input.Substring(i, 1);
                if (tmp != " ")
                {
                    returnStr.Append(tmp);
                }

            }
            return returnStr.ToString();
        }

        public String parseXmlToHex(String input)
        {
            String xml = removeSpaces(input);
            StringBuilder returnStr = new StringBuilder();

            for (int i = 0; i <= xml.Length - 2; i += 2)
            {
                String tmp = xml.Substring(i, 2);
                if (tmp != "0x")
                {
                    returnStr.Append(tmp);
                }
            }

            return returnStr.ToString();

        }

        private String HexString2Ascii(string input)
        {
            String hexString = parseXmlToHex(input);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= hexString.Length - 2; i += 2)
            {
                sb.Append(Convert.ToString(Convert.ToChar(Int32.Parse(hexString.Substring(i, 2), System.Globalization.NumberStyles.HexNumber))));
            }
            return sb.ToString();
        }

        private String decode()
        {
            return HexString2Ascii(getDataLabel(barcode));
        }

        //Scan a barcode
        public String scan()
        {
            var watch = System.Diagnostics.Stopwatch.StartNew();

            do
            {
                pullTrigger();
                readScan();
            } while (!barcodeIsRead);
            releaseTrigger();

            watch.Stop();
            int elapsedMs = (int)watch.ElapsedMilliseconds;
            times.Add(elapsedMs);
            barcodeIsRead = false;
            return decode();

        }

        public double avgTime()
        {
            int sum = 0;
            foreach (int i in times)
            {
                sum += i;
            }
            return sum / times.Count;
        }





    }
}

