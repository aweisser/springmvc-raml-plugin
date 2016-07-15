package com.phoenixnap.oss.ramlapisync.raml.jrp.raml08v2;

import com.google.gson.stream.JsonWriter;
import com.phoenixnap.oss.ramlapisync.raml.RamlModelEmitter;
import com.phoenixnap.oss.ramlapisync.raml.RamlRoot;
import org.raml.v2.api.model.v08.api.Api;
import org.raml.yagi.framework.model.ModelUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author armin.weisser
 */
public class Jrp08V2RamlModelEmitter implements RamlModelEmitter {
    @Override
    public String dump(RamlRoot ramlRoot) {
        Api api = ((Jrp08V2RamlRoot) ramlRoot).getApi();
        final StringWriter out = new StringWriter();
        final JsonWriter jsonWriter = new JsonWriter(out);
        jsonWriter.setIndent(" ");
        try {
            dumpToJson(Api.class, api, jsonWriter);
            return out.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Taken from org.raml.v2.api.ApiModelParserTestCase
    private void dumpToJson(Type definitionClass, Object value, JsonWriter jsonWriter) throws IOException, InvocationTargetException, IllegalAccessException
    {

        if (value == null)
        {
            jsonWriter.nullValue();
        }
        else if (value instanceof String)
        {
            jsonWriter.value(value.toString());
        }
        else if (value instanceof Number)
        {
            jsonWriter.value((Number) value);
        }
        else if (value instanceof Boolean)
        {
            jsonWriter.value((Boolean) value);
        }
        else if (value.getClass().isEnum())
        {
            jsonWriter.value(value.toString());
        }
        else if (value instanceof List)
        {
            jsonWriter.beginArray();
            for (java.lang.Object o : ((List) value))
            {
                final Class<?> genericListType;
                if (o != null && o.getClass().getInterfaces().length > 0)
                {
                    genericListType = o.getClass().getInterfaces()[0];
                }
                else
                {
                    genericListType = (Class<?>) ((ParameterizedType) definitionClass).getActualTypeArguments()[0];
                }
                dumpToJson(genericListType, o, jsonWriter);
            }
            jsonWriter.endArray();
        }
        else
        {
            jsonWriter.beginObject();
            final Method[] declaredMethods = ModelUtils.toClass(definitionClass).getMethods();
            Arrays.sort(declaredMethods, new Comparator<Method>()
            {
                @Override
                public int compare(Method o1, Method o2)
                {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (Method declaredMethod : declaredMethods)
            {
                if (declaredMethod.getParameterTypes().length == 0)
                {
                    if (!isRecursiveMethod(declaredMethod))
                    {
                        final Object methodResult = declaredMethod.invoke(value);
                        jsonWriter.name(declaredMethod.getName());
                        dumpToJson(declaredMethod.getGenericReturnType(), methodResult, jsonWriter);
                    }
                }
            }
            jsonWriter.endObject();
        }

    }

    private boolean isRecursiveMethod(Method declaredMethod)
    {
        return declaredMethod.getName().startsWith("parent")
                ||
                (declaredMethod.getDeclaringClass().getSimpleName().equals("Method") && declaredMethod.getName().equals("resource"));
    }

}
