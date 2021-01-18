

export class DocumentDto {

  file_name: string;
  archive_format: string;
  encodedDoc: string;
  contextDtoIn: ContextDtoIn = new ContextDtoIn();

}

class ContextDtoIn {

  conserv_unit_id: number;
  mine_type: string;
  archiving_reference_date: Date;
  final_business_processing_date: Date;
  frozen_label: string;
  hold_status: boolean;
  frozen: boolean;
  final_hold_date: Date;
  deletion_date: Date;
  classification_natureId: number;
  eventType: number = 0;
  customerId: number;
  contractId: number;
  accountId: number;

}
