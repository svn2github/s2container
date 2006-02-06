package org.seasar.framework.xml;

import junit.framework.TestCase;

import org.xml.sax.Attributes;

public class SaxHandlerTest extends TestCase {

    private static final String XML_FILE_NAME = "org/seasar/framework/xml/test1.xml";

    private TagHandlerRule rule_;

    public void testStart() throws Exception {
        rule_.addTagHandler("/tag1", new TagHandler() {
            private static final long serialVersionUID = -4675761325253965494L;

            public void start(TagHandlerContext ctx, Attributes attributes) {
                ctx.push(attributes.getValue("attr1"));
            }
        });
        SaxHandler handler = new SaxHandler(rule_);
        SaxHandlerParser parser = new SaxHandlerParser(handler);
        assertEquals("1", "aaa", parser.parse(XML_FILE_NAME));
    }

    public void testAppendBody() throws Exception {
        final StringBuffer buf = new StringBuffer();
        rule_.addTagHandler("/tag1", new TagHandler() {
            private static final long serialVersionUID = -4847079118886382658L;

            public void appendBody(TagHandlerContext ctx, String body) {
                buf.append("[" + body + "]");
            }
        });
        SaxHandler handler = new SaxHandler(rule_);
        SaxHandlerParser parser = new SaxHandlerParser(handler);
        parser.parse(XML_FILE_NAME);
        System.out.println(buf);
        assertEquals("1", "[111][222][333]", buf.toString());
    }

    public void testAppendBody2() throws Exception {
        final StringBuffer buf = new StringBuffer();
        rule_.addTagHandler("tag1", new TagHandler() {
            private static final long serialVersionUID = -8151502830088127159L;

            public void appendBody(TagHandlerContext ctx, String body) {
                buf.append("[" + body + "]");
            }
        });
        SaxHandler handler = new SaxHandler(rule_);
        SaxHandlerParser parser = new SaxHandlerParser(handler);
        parser.parse(XML_FILE_NAME);
        System.out.println(buf);
        assertEquals("1", "[111][222][333]", buf.toString());
    }

    public void testAppendBody3() throws Exception {
        final StringBuffer buf = new StringBuffer();
        rule_.addTagHandler("/tag1/tag3/tag4", new TagHandler() {
            private static final long serialVersionUID = 7554102451258851145L;

            public void appendBody(TagHandlerContext ctx, String body) {
                buf.append("[" + body + "]");
            }
        });
        SaxHandler handler = new SaxHandler(rule_);
        SaxHandlerParser parser = new SaxHandlerParser(handler);
        parser.parse(XML_FILE_NAME);
        System.out.println(buf);
        assertEquals("1", "[eee]", buf.toString());
    }

    public void testEnd() throws Exception {
        rule_.addTagHandler("/tag1/tag2", new TagHandler() {
            private static final long serialVersionUID = -549136729563029588L;

            public void end(TagHandlerContext ctx, String body) {
                ctx.push(body);
            }
        });
        SaxHandler handler = new SaxHandler(rule_);
        SaxHandlerParser parser = new SaxHandlerParser(handler);
        Object result = parser.parse(XML_FILE_NAME);
        assertEquals("1", "c c", result);
    }

    public void testException() throws Exception {
        rule_.addTagHandler("/tag1/tag3", new TagHandler() {
            private static final long serialVersionUID = -7435868325103101164L;

            public void start(TagHandlerContext ctx, Attributes attributes) {
                throw new RuntimeException("testException");
            }
        });
        try {
            SaxHandler handler = new SaxHandler(rule_);
            SaxHandlerParser parser = new SaxHandlerParser(handler);
            parser.parse(XML_FILE_NAME);
            fail("1");
        } catch (RuntimeException ex) {
            System.out.println(ex);
        }
    }

    public void testTagMatching() throws Exception {
        TagHandler eh = new TagHandler() {
            private static final long serialVersionUID = 313427123032197039L;

            public void start(TagHandlerContext ctx, Attributes attributes) {
                System.out.println(ctx.getDetailPath());
            }
        };
        rule_.addTagHandler("tag1", eh);
        rule_.addTagHandler("tag2", eh);
        rule_.addTagHandler("tag3", eh);
        rule_.addTagHandler("tag4", eh);
        rule_.addTagHandler("tag5", eh);
        System.out.println("tagMatching");
        SaxHandler handler = new SaxHandler(rule_);
        SaxHandlerParser parser = new SaxHandlerParser(handler);
        parser.parse(XML_FILE_NAME);
    }

    protected void setUp() throws Exception {
        rule_ = new TagHandlerRule();
    }
}