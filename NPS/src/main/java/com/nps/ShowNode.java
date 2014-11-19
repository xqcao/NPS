package com.nps;

import org.apache.log4j.Logger;
import org.finra.datagenerator.consumer.DataConsumer;
import org.finra.datagenerator.distributor.multithreaded.DefaultDistributor;
import org.finra.datagenerator.engine.Engine;
import org.finra.datagenerator.engine.scxml.SCXMLEngine;
import org.apache.log4j.Level;
import org.finra.datagenerator.writer.DefaultWriter;

import com.nps.transformer.TempTransformer;

import java.io.InputStream;


public final class ShowNode {

	private ShowNode() {
		}
	private static final Logger log = Logger.getLogger(ShowNode.class);
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Engine engine = new SCXMLEngine();
		InputStream is = ShowNode.class.getResourceAsStream("/" + (args.length == 0 ? "samplemachine" : args[0]) + ".xml");
		engine.setModelByInputFileStream(is);
		engine.setBootstrapMin(1);		
		DataConsumer consumer = new DataConsumer();
		consumer.addDataTransformer(new TempTransformer());
		consumer.addDataWriter(new DefaultWriter(System.out, new String[]{"sizeOFBox", "region", "transport","insurance","arrival",  "var_out_V1"}));
		DefaultDistributor defaultDistributor = new DefaultDistributor();
		defaultDistributor.setThreadCount(1);
		defaultDistributor.setDataConsumer(consumer);
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		engine.process(defaultDistributor);

		
		System.out.println("totle Entry numbers : " + new TempTransformer().count());		
		long end = System.currentTimeMillis();
		System.out.println("time cost : " + (end-start));
		System.out.println("Used Memory   :  " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes");
        System.out.println("Free Memory   : " + Runtime.getRuntime().freeMemory() + " bytes");
        System.out.println("Total Memory  : " + Runtime.getRuntime().totalMemory() + " bytes");
        System.out.println("Max Memory    : " + Runtime.getRuntime().maxMemory() + " bytes");           
	}
}
