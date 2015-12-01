package com.teampc.model;

import com.teampc.dao.DataDefinition;
import com.teampc.dao.HasId;

/**
 * Indicates that an object can be transformed to a DD object.
 */
public interface Model<D extends DataDefinition> extends HasId {
   public D asEntity();
}
