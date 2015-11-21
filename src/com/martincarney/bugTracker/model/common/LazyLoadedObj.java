package com.martincarney.bugTracker.model.common;


public abstract class LazyLoadedObj<T extends Object> {
	
	/**
	 * Fully-loaded object, with all attributes needed. Should be immutable once loaded,
	 * though the object's attributes may remain mutable.
	 */
	protected T fullObject;
	
	/**
	 * Database id of the object to be used when fully loading the object.
	 */
	protected long objectId;
	
	/**
	 * Displayable name of the object.
	 */
	protected String objectName;
	
	public LazyLoadedObj(long id, String name) {
		this.objectId = id;
		this.objectName = name;
	}
	
	/**
	 * Fully loads the object if it hasn't already been loaded.
	 * @return the fully-loaded object
	 */
	public abstract T getObj();
	
	public long getObjId() {
		return objectId;
	}
	
	public String getObjName() {
		return objectName;
	}
	
	public boolean isLoaded() {
		return fullObject == null;
	}
	
	/**
	 * Optional additional display attribute which can be used alongside the object's name. If unimplemented,
	 * this will always return {@code null}.
	 */
	public String getAttributeA() {
		return null;
	}
	
	/**
	 * Sets an optional, additional display attribute. If unimplemented, this method is a no-op.
	 */
	public void setAttributeA(String attributeA) {}
	
	/**
	 * Optional additional display attribute which can be used alongside the object's name and AttributeA.
	 * If unimplemented, this will always return {@code null}.
	 */
	public String getAttributeB() {
		return null;
	}
	
	/**
	 * Sets an optional, additional display attribute. If unimplemented, this method is a no-op.
	 */
	public void setAttributeB(String attributeB) {}
	
}
