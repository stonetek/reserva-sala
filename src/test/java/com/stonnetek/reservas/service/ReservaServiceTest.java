package com.stonnetek.reservas.service;

import com.stonnetek.reservas.entity.Reserva;
import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.enums.StatusReserva;
import com.stonnetek.reservas.enums.TipoSala;
import com.stonnetek.reservas.exception.RegraNegocioException;
import com.stonnetek.reservas.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private SalaService salaService;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void deveCriarReservaComSucesso() {

        Sala sala = new Sala(
                1L,
                "Auditório",
                TipoSala.AUDITORIO,
                100,
                null
        );

        Reserva reserva = new Reserva();

        reserva.setResponsavel("Petrus");
        reserva.setData(LocalDate.now().plusDays(1));
        reserva.setHoraInicio(LocalTime.of(14, 0));
        reserva.setHoraFim(LocalTime.of(15, 0));
        reserva.setSala(sala);

        when(salaService.buscarPorId(1L))
                .thenReturn(sala);

        when(reservaRepository.existeConflito(
                anyLong(),
                any(),
                any(),
                any()
        )).thenReturn(false);

        when(reservaRepository.save(any()))
                .thenReturn(reserva);

        Reserva resultado = reservaService.criar(reserva);

        assertNotNull(resultado);

        assertEquals(
                StatusReserva.ATIVA,
                resultado.getStatus()
        );

        verify(reservaRepository, times(1))
                .save(any());
    }


    @Test
    void deveLancarExcecaoQuandoExistirConflito() {

        Sala sala = new Sala(
                1L,
                "Sala Executiva",
                TipoSala.COLETIVA,
                10,
                null
        );

        Reserva reserva = new Reserva();

        reserva.setData(LocalDate.now().plusDays(1));
        reserva.setHoraInicio(LocalTime.of(14, 0));
        reserva.setHoraFim(LocalTime.of(15, 0));
        reserva.setSala(sala);

        when(salaService.buscarPorId(1L))
                .thenReturn(sala);

        when(reservaRepository.existeConflito(
                anyLong(),
                any(),
                any(),
                any()
        )).thenReturn(true);

        RegraNegocioException exception =
                assertThrows(
                        RegraNegocioException.class,
                        () -> reservaService.criar(reserva)
                );

        assertEquals(
                "Já existe uma reserva nesse horário.",
                exception.getMessage()
        );

        verify(reservaRepository, never())
                .save(any());
    }


    @Test
    void deveLancarExcecaoQuandoHoraFimForMenor() {

        Sala sala = new Sala();

        sala.setId(1L);

        Reserva reserva = new Reserva();

        reserva.setData(LocalDate.now().plusDays(1));
        reserva.setHoraInicio(LocalTime.of(15, 0));
        reserva.setHoraFim(LocalTime.of(14, 0));
        reserva.setSala(sala);

        RegraNegocioException exception =
                assertThrows(
                        RegraNegocioException.class,
                        () -> reservaService.criar(reserva)
                );

        assertEquals(
                "Hora fim deve ser maior que hora início.",
                exception.getMessage()
        );
    }


    @Test
    void deveCancelarReserva() {

        Reserva reserva = new Reserva();

        reserva.setId(1L);
        reserva.setStatus(StatusReserva.ATIVA);

        when(reservaRepository.findById(1L))
                .thenReturn(java.util.Optional.of(reserva));

        when(reservaRepository.save(any()))
                .thenReturn(reserva);

        Reserva resultado = reservaService.cancelar(1L);

        assertEquals(
                StatusReserva.CANCELADA,
                resultado.getStatus()
        );

        verify(reservaRepository, times(1))
                .save(reserva);
    }

    @Test
    void deveLancarExcecaoQuandoReservaJaEstiverCancelada() {

        Reserva reserva = new Reserva();

        reserva.setId(1L);
        reserva.setStatus(StatusReserva.CANCELADA);

        when(reservaRepository.findById(1L))
                .thenReturn(java.util.Optional.of(reserva));

        RegraNegocioException exception =
                assertThrows(
                        RegraNegocioException.class,
                        () -> reservaService.cancelar(1L)
                );

        assertEquals(
                "A reserva já está cancelada.",
                exception.getMessage()
        );

        verify(reservaRepository, never())
                .save(any());
    }

}
