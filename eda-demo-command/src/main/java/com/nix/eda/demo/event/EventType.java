/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.nix.eda.demo.event;
@org.apache.avro.specific.AvroGenerated
public enum EventType implements org.apache.avro.generic.GenericEnumSymbol<EventType> {
  OrderPlaced, OrderProcessed, OrderCancelled  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"EventType\",\"namespace\":\"com.nix.eda.demo.event\",\"symbols\":[\"OrderPlaced\",\"OrderProcessed\",\"OrderCancelled\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}
