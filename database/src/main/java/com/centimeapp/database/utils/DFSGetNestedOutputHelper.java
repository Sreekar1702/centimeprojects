package com.centimeapp.database.utils;

import com.centimeapp.database.model.NestedOutput;
import com.centimeapp.database.model.NestedOutputWithSubclasses;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import java.util.*;

public class DFSGetNestedOutputHelper {

    private Map<Integer, List<NestedOutput>> parentIdvsUsersMap = new HashMap<>();

    /**
     * Create map of parentid vs arraylist of common parentid objects
     * @param allNames - userdetails list
     */
    public DFSGetNestedOutputHelper(List<NestedOutput> allNames) {
        for (NestedOutput nestedOutput : allNames) {
            if (!parentIdvsUsersMap.containsKey(nestedOutput.getParentid())) {
                parentIdvsUsersMap.put(nestedOutput.getParentid(), new ArrayList<>());
            }
            parentIdvsUsersMap.get(nestedOutput.getParentid()).add(nestedOutput);
        }
    }

    @ApiOperation("The updateHierarchy method recursively updates the hierarchy of NestedOutputWithSubclasses using a depth-first traversal")
    public List<NestedOutputWithSubclasses> updateHierarchy() {

        List<NestedOutputWithSubclasses> nestedOutputWithSubclasses = new ArrayList<>();
        List<NestedOutput> nestedOutputs = parentIdvsUsersMap.get(0);
        for (NestedOutput nestedOutput : nestedOutputs) {
            NestedOutputWithSubclasses employeeWithSubclasses = new NestedOutputWithSubclasses(nestedOutput.getName(), getHierarchy(nestedOutput.getId(), parentIdvsUsersMap));
            nestedOutputWithSubclasses.add(employeeWithSubclasses);
        }
        return nestedOutputWithSubclasses;
    }

    private static List<NestedOutputWithSubclasses> getHierarchy(int id, Map<Integer, List<NestedOutput>> parentIdvsUsersMap) {
        List<NestedOutputWithSubclasses> subclasses = new ArrayList<>();
        List<NestedOutput> nestedOutputs = parentIdvsUsersMap.get(id);
        if (!CollectionUtils.isEmpty(nestedOutputs)) {
            for (NestedOutput nestedOutput : nestedOutputs) {
                NestedOutputWithSubclasses employeeWithSubclasses = new NestedOutputWithSubclasses(nestedOutput.getName(), getHierarchy(nestedOutput.getId(), parentIdvsUsersMap));
                subclasses.add(employeeWithSubclasses);
            }
        }
        return subclasses;
    }
}

