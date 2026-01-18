package ec.com.ecommerce.global_service.modules.time_periods.adapters.grpc;

import com.google.protobuf.Empty;
import ec.com.ecommerce.grpc_lib.global.RequestTimePeriodById;
import ec.com.ecommerce.grpc_lib.global.TimePeriodGrpcResponse;
import ec.com.ecommerce.grpc_lib.global.TimePeriodListGrpcResponse;
import ec.com.ecommerce.grpc_lib.global.TimePeriodServiceGrpc;
import ec.com.ecommerce.global_service.modules.time_periods.application.mapper.TimePeriodMapper;
import ec.com.ecommerce.global_service.modules.time_periods.application.service.TimePeriodService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TimePeriodGrpcController extends TimePeriodServiceGrpc.TimePeriodServiceImplBase {
    private final TimePeriodService service;
    private final TimePeriodMapper mapper;

    public TimePeriodGrpcController(TimePeriodService service, TimePeriodMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public void getAllTimePeriods(Empty request, StreamObserver<TimePeriodListGrpcResponse> responseObserver) {
        try {
            var timePeriods = service.retrieve(Map.of());
            var grpcList = timePeriods.map(mapper::toGrpc)
                                      .filter(java.util.Objects::nonNull)
                                      .toList();
            log.debug("Mapped time periods for gRPC: {}", grpcList);
            TimePeriodListGrpcResponse response = TimePeriodListGrpcResponse.newBuilder()
                    .addAllTimePeriods(grpcList)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Error retrieving time periods: {}", e.getMessage(), e);
            responseObserver.onError(e);
        }
    }

    @Override
    public void getTimePeriodById(RequestTimePeriodById request, StreamObserver<TimePeriodGrpcResponse> responseObserver) {
        try {
            TimePeriodGrpcResponse response = service.retrieveGrpcById(request.getId());
            if (response != null) {
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new Exception("Time period not found for ID: " + request.getId()));
            }
        } catch (Exception e) {
            log.error("Error retrieving time period by ID {}: {}", request.getId(), e.getMessage());
            responseObserver.onError(e);
        }
    }
}
