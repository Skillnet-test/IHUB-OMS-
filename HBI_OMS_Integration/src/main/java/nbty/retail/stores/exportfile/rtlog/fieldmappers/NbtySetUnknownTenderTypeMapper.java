/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.             DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.     02/1/2018      Ikshita      Defect #3965339                 Credit Card type is unknown and it fails in ReSA.
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/

package nbty.retail.stores.exportfile.rtlog.fieldmappers;

import oracle.retail.stores.exportfile.ExportFileException;
import oracle.retail.stores.exportfile.formater.FieldFormatIfc;
import oracle.retail.stores.exportfile.formater.RecordFormatIfc;
import oracle.retail.stores.exportfile.mapper.ColumnMapIfc;
import oracle.retail.stores.exportfile.mapper.EntityMapperIfc;
import oracle.retail.stores.exportfile.mapper.FieldMapper;
import oracle.retail.stores.exportfile.mapper.FieldMapperIfc;
import oracle.retail.stores.exportfile.rtlog.RTLogRecordFormatConstantsIfc;
import oracle.retail.stores.xmlreplication.result.EntityIfc;
import oracle.retail.stores.xmlreplication.result.Row;

public class NbtySetUnknownTenderTypeMapper extends FieldMapper implements FieldMapperIfc, RTLogRecordFormatConstantsIfc
{
    public int map(String columnValue, Row row, ColumnMapIfc columnMap, FieldFormatIfc field, RecordFormatIfc record,
            EntityIfc entity, EntityMapperIfc entityMapper) throws ExportFileException
    {
        field.setValue("3120");
        return 1;
    }
}