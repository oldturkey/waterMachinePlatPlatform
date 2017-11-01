/*
 * Copyright Notice:
 *      Copyright  1998-2008, Huawei Technologies Co., Ltd.  ALL Rights Reserved.
 *
 *      Warning: This computer software sourcecode is protected by copyright law
 *      and international treaties. Unauthorized reproduction or distribution
 *      of this sourcecode, or any portion of it, may result in severe civil and
 *      criminal penalties, and will be prosecuted to the maximum extent
 *      possible under the law.
 */
package com.terabits.utils;

import org.apache.http.*;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.Locale;

@SuppressWarnings("deprecation")
public class StreamClosedHttpResponse implements HttpResponse
{
    private final HttpResponse original;

    private final String content;

    public StreamClosedHttpResponse(final HttpResponse original)
            throws UnsupportedOperationException, IOException
    {
        this.original = original;
        HttpEntity entity = original.getEntity();
        if (entity != null && entity.isStreaming())
        {
            String encoding = entity.getContentEncoding() != null
                    ? entity.getContentEncoding().getValue() : null;
            content = StreamUtil.inputStream2String(entity.getContent(),
                    encoding);
        }
        else
        {
            content = null;
        }
    }


    public StatusLine getStatusLine()
    {
        return original.getStatusLine();
    }


    public void setStatusLine(final StatusLine statusline)
    {
        original.setStatusLine(statusline);
    }


    public void setStatusLine(final ProtocolVersion ver, final int code)
    {
        original.setStatusLine(ver, code);
    }


    public void setStatusLine(final ProtocolVersion ver, final int code,
                              final String reason)
    {
        original.setStatusLine(ver, code, reason);
    }


    public void setStatusCode(final int code) throws IllegalStateException
    {
        original.setStatusCode(code);
    }


    public void setReasonPhrase(final String reason)
            throws IllegalStateException
    {
        original.setReasonPhrase(reason);
    }


    public HttpEntity getEntity()
    {
        return original.getEntity();
    }


    public void setEntity(final HttpEntity entity)
    {
        original.setEntity(entity);
    }


    public Locale getLocale()
    {
        return original.getLocale();
    }


    public void setLocale(final Locale loc)
    {
        original.setLocale(loc);
    }


    public ProtocolVersion getProtocolVersion()
    {
        return original.getProtocolVersion();
    }


    public boolean containsHeader(final String name)
    {
        return original.containsHeader(name);
    }


    public Header[] getHeaders(final String name)
    {
        return original.getHeaders(name);
    }


    public Header getFirstHeader(final String name)
    {
        return original.getFirstHeader(name);
    }


    public Header getLastHeader(final String name)
    {
        return original.getLastHeader(name);
    }


    public Header[] getAllHeaders()
    {
        return original.getAllHeaders();
    }


    public void addHeader(final Header header)
    {
        original.addHeader(header);
    }


    public void addHeader(final String name, final String value)
    {
        original.addHeader(name, value);
    }


    public void setHeader(final Header header)
    {
        original.setHeader(header);
    }


    public void setHeader(final String name, final String value)
    {
        original.setHeader(name, value);
    }


    public void setHeaders(final Header[] headers)
    {
        original.setHeaders(headers);
    }


    public void removeHeader(final Header header)
    {
        original.removeHeader(header);
    }


    public void removeHeaders(final String name)
    {
        original.removeHeaders(name);
    }


    public HeaderIterator headerIterator()
    {
        return original.headerIterator();
    }


    public HeaderIterator headerIterator(final String name)
    {
        return original.headerIterator(name);
    }


    public String toString()
    {
        final StringBuilder sb = new StringBuilder("HttpResponseProxy{");
        sb.append(original);
        sb.append('}');
        return sb.toString();
    }


    @Deprecated
    public HttpParams getParams()
    {
        return original.getParams();
    }


    @Deprecated
    public void setParams(final HttpParams params)
    {
        original.setParams(params);
    }

    public String getContent()
    {
        return content;
    }
}
