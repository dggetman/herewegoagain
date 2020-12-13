package ru.ssau.tk._onimeshki_._herewegoagain_.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel {
    private static final int INDEX_COLUMN_NUMBER = 0;
    private static final int X_COLUMN_NUMBER = 1;
    private static final int Y_COLUMN_NUMBER = 2;
    private static final long serialVersionUID = -6745685786311274493L;
    private final List<Double> xValues;
    private final List<Double> yValues;

    public TableModel(List<Double> xValues, List<Double> yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    @Override
    public int getRowCount() {
        return xValues.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case INDEX_COLUMN_NUMBER:
                return rowIndex;
            case X_COLUMN_NUMBER:
                return xValues.get(rowIndex);
            case Y_COLUMN_NUMBER:
                return yValues.get(rowIndex);
        }
        throw new UnsupportedOperationException();
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) throws NumberFormatException {
        if (columnIndex == X_COLUMN_NUMBER) {
            try {
                xValues.set(rowIndex, Double.valueOf(aValue.toString()));
            } catch (Exception e) {
                xValues.set(rowIndex, 0.0);
            }
        } else if (columnIndex == Y_COLUMN_NUMBER) {
            try {
                yValues.set(rowIndex, Double.valueOf(aValue.toString()));
            } catch (Exception e) {
                yValues.set(rowIndex, 0.0);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case X_COLUMN_NUMBER, Y_COLUMN_NUMBER -> true;
            default -> false;
        };
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case INDEX_COLUMN_NUMBER -> "Индекс";
            case X_COLUMN_NUMBER -> "X";
            case Y_COLUMN_NUMBER -> "Y";
            default -> super.getColumnName(column);
        };
    }
}
