package org.htmltranslationextract.impl;

import org.htmltranslationextract.TextStorer;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

public class NodeVisitorImpl implements NodeVisitor {
	
	private TextStorer ts;
	
	public NodeVisitorImpl(TextStorer ts){
		this.ts = ts;
	}
	
	@Override
	public void head(Node node, int depth) {
        // TextNode
        if(node instanceof TextNode){
        	TextNode tn = (TextNode) node;
        	if(! tn.isBlank()){
				tn.text(ts.store(tn.text()));
        	}
        } else {
        	String[] checkAttr = new String[]{"alt", "src", "title"};
        	for(String attr: checkAttr){
        		if(node.attributes().hasKey(attr)){
            		node.attr(attr, ts.store(node.attr(attr)));
            	}
        	}
        }
    }

	@Override
	public void tail(Node node, int depth) {
        //System.out.println("Exiting tag: " + node.nodeName());
    }

}
