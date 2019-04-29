package com.google.gwt.user.client.rpc

import groovy.transform.TypeChecked
import org.codehaus.groovy.runtime.StackTraceUtils

@TypeChecked
class StackTrace {

    static String stackTraceAsString(Throwable t) {
        StringWriter out = new StringWriter()
        PrintWriter writer = new PrintWriter(out)

        StackTraceUtils.printSanitizedStackTrace(t, writer)

        writer.flush(); // flush is really optional here, as Writer calls the empty StringWriter.flush
        out.toString()
    }

    static String projectLinesOnly(String s) {
        StringBuilder buf = new StringBuilder()

        String proj = 'gov.nist.asbestos'

        if (!s.contains(proj))
            return s

        List<String> arr = []

        s.eachLine { String line ->
            arr.add(line)
        }

        boolean header = true

        for (String line : arr) {
            if (header) {
                buf.append(line).append('\n')
                if (line.contains(proj))
                    header = false
            }
            if (line.contains(proj)) {
                buf.append(line).append('\n')
            }
        }

        buf.toString()
    }
}
