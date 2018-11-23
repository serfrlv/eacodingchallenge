package com.shine.eacodingchallenge.service.renderer;

import com.shine.eacodingchallenge.domain.ModelData;
import com.shine.eacodingchallenge.domain.MakeData;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * A simple HTML renderer
 */
@Service
public class HtmlCarDataRendererService implements IVendorDataRenderer {

    static final String NO_DATA = "No data";

    private static final String MAKE_TEMPLATE = "<span>%s</span><br/>";
    private static final String MODEL_TEMPLATE = "<span style=\"margin-left:2em\">%s</span><br/>";
    private static final String SHOW_TEMPLATE = "<span style=\"margin-left:4em\">%s</span><br/>";

    /**
     * Renders provided {@link MakeData} into HTML
     * @param makeData
     * @return
     */
    @Override
    public String render(Map<String, MakeData> makeData) {

        if (makeData == null || makeData.size() == 0){
            return NO_DATA;
        }

        StringBuilder output = new StringBuilder();
        makeData.keySet().stream().filter(Objects::nonNull).sorted().forEach(makeName -> { //filter and solt by make name

            MakeData make = makeData.get(makeName);

            if (make != null && make.getName() != null && ! make.getName().trim().isEmpty()) { //if make data exist for the key and is correct

                output.append(String.format(MAKE_TEMPLATE, make.getName()));

                Map<String, ModelData> models = make.getModels();
                if (models != null) { //if this make has models
                    models.keySet().stream().filter(Objects::nonNull).sorted().forEach(modelName -> { //filter and sort by model name

                        ModelData modelData = models.get(modelName);

                        if (modelData != null && modelData.getName() != null && ! modelData.getName().trim().isEmpty()) { //if model data exist for the key is correct

                            output.append(String.format(MODEL_TEMPLATE, modelName));

                            Set<String> attendedCarShows = modelData.getAttendedCarShows();
                            if (attendedCarShows != null) {//if this model attended car shows
                                attendedCarShows.stream().filter(Objects::nonNull).filter(showName -> ! showName.trim().isEmpty()).sorted() //filter and sort by car show name
                                    .forEach(showName -> output.append(String.format(SHOW_TEMPLATE, showName)));
                            }
                        }
                    });
                }
            }
        });
        return output.length() == 0 ? NO_DATA : output.toString();
    }
}

