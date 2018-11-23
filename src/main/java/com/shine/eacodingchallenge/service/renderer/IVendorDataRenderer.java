package com.shine.eacodingchallenge.service.renderer;

import com.shine.eacodingchallenge.domain.MakeData;
import java.util.Map;

/**
 * Defines a make data renderer which renders the representation of the provided data into a string
 */
public interface IVendorDataRenderer {

    /**
     * Converts a {@link Map} of {@link MakeData} stored against {@link String} keys into a string object
     * @param makeData
     * @return a string representing the provided data
     */
    String render(Map<String, MakeData> makeData);

}
