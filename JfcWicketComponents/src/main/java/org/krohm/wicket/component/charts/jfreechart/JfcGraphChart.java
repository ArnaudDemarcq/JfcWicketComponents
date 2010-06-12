/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.wicket.component.charts.jfreechart;

import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.krohm.wicket.component.charts.jfreechart.util.AbstractChartImage;

/**
 *
 * @author arnaud
 */
public abstract class JfcGraphChart extends AbstractChartImage {

    protected int height = 250;
    protected int width = 250;
    protected String title = "";
    protected String XAxisLabel = "";
    protected String YAxisLabel = "";

    public JfcGraphChart(String id) {
        super(id);
    }

    // Data Required for a Histogram Chart
    public abstract Map<String, Map<Number, Number>> getData();

    public JFreeChart getJFreeChart() {

        Map<String, Map<Number, Number>> data = getData();
        XYSeriesCollection dataSet = new XYSeriesCollection();

        for
         (String key : data.keySet()) {
            XYSeries currentSeries = new XYSeries(key);
            Map<Number, Number> currentDataMap = data.get(key);
            for (Number xValue : currentDataMap.keySet()) {
                Number yValue = currentDataMap.get(xValue);
                currentSeries.add(xValue, yValue);
            }
            dataSet.addSeries(currentSeries);
        }


        JFreeChart chart = ChartFactory.createXYLineChart(getTitle(), getXAxisLabel(), getYAxisLabel(),
                dataSet, PlotOrientation.VERTICAL, true, true, false);

        return chart;
    }

    //
    // BeanLike Abilities
    //
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getXAxisLabel() {
        return XAxisLabel;
    }

    public void setXAxisLabel(String XAxisLabel) {
        this.XAxisLabel = XAxisLabel;
    }

    public String getYAxisLabel() {
        return YAxisLabel;
    }

    public void setYAxisLabel(String YAxisLabel) {
        this.YAxisLabel = YAxisLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
