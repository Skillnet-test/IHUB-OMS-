package oracle.retail.stores.exportfile.rtlog.fieldmappers;

import oracle.retail.stores.common.utility.StringUtils;
import oracle.retail.stores.exportfile.ExportFileException;
import oracle.retail.stores.exportfile.formater.FieldFormatIfc;
import oracle.retail.stores.exportfile.formater.RecordFormatIfc;
import oracle.retail.stores.exportfile.mapper.ColumnMapIfc;
import oracle.retail.stores.exportfile.mapper.EntityMapperIfc;
import oracle.retail.stores.exportfile.mapper.FieldMapper;
import oracle.retail.stores.exportfile.mapper.FieldMapperIfc;
import oracle.retail.stores.xmlreplication.result.EntityIfc;
import oracle.retail.stores.xmlreplication.result.Row;

public class BusinessDateMapper extends FieldMapper implements FieldMapperIfc
{
    public int map(String columnValue, Row row, ColumnMapIfc columnMap, FieldFormatIfc field, RecordFormatIfc record,
            EntityIfc entity, EntityMapperIfc entityMapper) throws ExportFileException
    {
        if(StringUtils.isNotEmpty(columnValue))
        {
            StringBuffer bDate = new StringBuffer();
            bDate.append(columnValue.substring(0, 4));
            bDate.append(columnValue.substring(5, 7));
            bDate.append(columnValue.substring(8));
            field.setValue(bDate.toString());
        }
        else
            field.setValue("");
        return 1;
    }
}