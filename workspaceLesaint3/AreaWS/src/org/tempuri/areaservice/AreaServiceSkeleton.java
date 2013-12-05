
/**
 * AreaServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
    package org.tempuri.areaservice;
    /**
     *  AreaServiceSkeleton java skeleton for the axisService
     */
    public class AreaServiceSkeleton{
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param parameters1 
             * @return area 
         */
        
                 public org.tempuri.areaservice.Area calculateCircleArea
                  (
                  org.tempuri.areaservice.Parameters1 parameters1
                  )
            {
                	 Area area = new Area();
             		area.setArea((float) (parameters1.getParameters1().getRayon() * Math.PI));
             		return area;
                }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param parameters 
             * @return area0 
         */
        
                 public org.tempuri.areaservice.Area calculateRectArea
                  (
                  org.tempuri.areaservice.Parameters parameters
                  )
            {
             		Area area = new Area();
            		area.setArea(parameters.getParameters().getHeight()
            				* parameters.getParameters().getWidth());
            		return area;}
     
    }
    