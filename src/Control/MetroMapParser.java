package Control;

        import Exceptions.BadFileException;
        import Graph.IMultigraph;
        import Graph.MultiGraph;
        import Metro.Line;
        import Metro.Station;

        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.StringTokenizer;

/**
 * This class reads a text description of a metro subway system
 * and generates a graph representation of the metro.
 *
 * Students should feel free to modify this code as needed
 *  to complete this exercise.
 *
 *<p>
 *
 * The grammar for the file is described below in BNF. A typical line
 * in the file looks like this :
 *
 * <code> 20 NorthStation   Green 19 22  Orange 15 22  </code>
 *
 * where :
 *         20 is the StationID
 *         NorthStation is the StationName
 *         Green 19 22
 *                  Green is the LineName
 *                  19 is the StationID of the outbound station
 *                  22 is the StationID of the inbound station
 *         Orange 15 22 is a LineID in which :
 *                  Orange is the LineName
 *                  15 is the StationID of the outbound station
 *                  22 is the StationID of the inbound station
 *
 *         Therefore, NorthStation has two outgoing lines.
 *
 *  note : 0 denotes the end of a line : i.e. in this case,
 *  OakGrove would be at the end of the line, as there is no other outbound
 *  station.
 *
 *<p>
 * metro-map ::= station-spec* <BR>
 * station-spec ::= station-id station-name station-line+ <BR>
 * station-id ::= (positive integer) <BR>
 * station-name ::= string <BR>
 * station-line ::= line-name station-id station-id <BR>
 *
         */

    public class MetroMapParser
    {

    private BufferedReader fileInput;


//    public static void main(String[] args)
//    {
//
//        if(args.length != 1)
//        {
//            usage();
//            System.exit(0);
//        }
//
//        String filename = args[0];
//
//
//        try
//        {
//            MetroMapParser mmp = new MetroMapParser(filename);
//            mmp.generateGraphFromFile();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }


    private static void usage()
    {
        //prints a usage message to System.out
        System.out.println("java ex3.MetroMapParser <filename>");
    }




    /**
     * @effects: creates a new parser that will read from the file
     * filename unless the file does not exist. The filename should specify
     * the exact location of the file. This means it should be something like
     * /mit/$USER/6.170/ex3/bostonmetro.txt
     *
     *
     * @throws java.io.IOException if there <tt>filename</tt> cannot be read
     *
     * @returns a new MetroMapParser that will parse the file filename
     */

    public MetroMapParser(String filename) throws IOException
    {
        //a buffered reader reads line by line, returning null when file is done
        fileInput = new BufferedReader(new FileReader(filename));
    }

    /**
     * @effects: parses the file, and generates a graph from it, unless there
     * is a problem reading the file, or there is a problem with the format of the
     * file.
     *
     * @throws java.io.IOException if there is a problem reading the file
     * @throws Exceptions.BadFileException if there is a problem with the format of the file
     *
     * @returns the Graph generated by the file
     */

    public IMultigraph generateGraphFromFile()
            throws IOException, BadFileException
    {

        IMultigraph graph = new MultiGraph();

        String line = fileInput.readLine();
        StringTokenizer st;
        String stationID;
        String stationName;
        String lineName;
        String outboundID, inboundID;

        while(line != null)
        {

            //STUDENT :
            //
            //in this loop, you must collect the information necessary to
            //construct your graph, and you must construct your graph as well.
            //how and where you do this will depend on the design of your graph.
            //


            //StringTokenizer is a java.util Class that can break a string into tokens
            // based on a specified delimiter.  The default delimiter is " \t\n\r\f" which
            // corresponds to the space character, the tab character, the newline character,
            // the carriage-return character and the form-feed character.
            st = new StringTokenizer(line);

            //We want to handle empty lines effectively, we just ignore them!
            if(!st.hasMoreTokens())
            {
                line = fileInput.readLine();
                continue;
            }

            //from the grammar, we know that the Station ID is the first token on the line
            stationID = st.nextToken();

            if(!st.hasMoreTokens())
            {
                throw new BadFileException("no station name");
            }

            //from the grammar, we know that the Station Name is the second token on the line.
            stationName = st.nextToken();

            if(!st.hasMoreTokens())
            {
                throw new BadFileException("station is on no lines");
            }
//            System.out.println("Station: " + stationID + "-" + stationName);


            Station thisStation = new Station(Integer.parseInt(stationID), stationName);

            if(!graph.addNode(thisStation)) {
                ((Station)graph.getNode(Integer.parseInt(stationID))).setName(stationName);
            }

            while(st.hasMoreTokens())
            {
                lineName = st.nextToken();

                if(!st.hasMoreTokens())
                {
                    throw new BadFileException("poorly formatted line info");
                }

                outboundID = st.nextToken();

                Integer outbound = Integer.parseInt(outboundID);

                if(!st.hasMoreTokens())
                {
                    throw new BadFileException("poorly formatted adjacent stations");
                }

                inboundID = st.nextToken();
                Integer inbound = Integer.parseInt(inboundID);

//                System.out.println("Edge: " + outboundID + "-" + lineName + "-" + stationID);
//                System.out.println("Edge: " + stationID + "-" + lineName + "-" + inboundID);


                if(!outboundID.equals("0")){
                    if(graph.getNode(outbound) == null){
                        Station newStation = new Station(outbound);
                        graph.addNode(newStation);
                    }
                    Line line1 = new Line(lineName,(Station)graph.getNode(outbound),thisStation);
                    graph.addEdge(line1);
                }


                if(!inboundID.equals("0")){
                    if(graph.getNode(inbound) == null){
                        Station newStation = new Station(inbound);
                        graph.addNode(newStation);
                    }
                    Line line1 = new Line(lineName,(Station)graph.getNode(inbound),thisStation);
                    graph.addEdge(line1);
                }

            }


            line = fileInput.readLine();
        }

        return graph;
    }


}





