SET ANSI_NULLS ON;
SET QUOTED_IDENTIFIER ON;
CREATE TABLE [dbo].[ClientsTours]
(
    [ClientTourId] [int] IDENTITY (1,1) NOT NULL,
    [ClientId]     [int]                NOT NULL,
    [TourId]       [int]                NOT NULL,
    CONSTRAINT [PK_ClientsTours] PRIMARY KEY CLUSTERED
        (
         [ClientTourId] ASC,
         [ClientId] ASC,
         [TourId] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
ALTER TABLE [dbo].[ClientsTours]
    WITH CHECK ADD CONSTRAINT [FK_ClientsTours_Clients] FOREIGN KEY ([ClientId])
        REFERENCES [dbo].[Clients] ([ClientId]);
ALTER TABLE [dbo].[ClientsTours]
    CHECK CONSTRAINT [FK_ClientsTours_Clients];
ALTER TABLE [dbo].[ClientsTours]
    WITH CHECK ADD CONSTRAINT [FK_ClientsTours_Tours] FOREIGN KEY ([TourId])
        REFERENCES [dbo].[Tours] ([TourId])
        ON DELETE CASCADE
ALTER TABLE [dbo].[ClientsTours]
    CHECK CONSTRAINT [FK_ClientsTours_Tours];
